package com.midea.shiro.service.impl;

import com.midea.model.SysResource;
import com.midea.model.SysUser;
import com.midea.service.SysResourceService;
import com.midea.service.SysUserService;
import com.midea.shiro.realm.HopeShiroRealm;
import com.midea.shiro.service.ShiroService;
import com.midea.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ShiroServiceImpl implements ShiroService {

    private final SysResourceService sysResourceService;
    private final SysUserService sysUserService;

    public ShiroServiceImpl(SysResourceService sysResourceService, SysUserService sysUserService) {
        this.sysResourceService = sysResourceService;
        this.sysUserService = sysUserService;
    }

    /***
     * 初始化权限
     * @return
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        /***
         * 配置访问权限
         * anon:所有url都都可以匿名访问
         * authc: 需要认证才能进行访问（此处指所有非匿名的路径都需要登陆才能访问），支付等,建议使用authc权限
         * user:配置记住我或认证通过可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //开放资源文件
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/verificationCode", "anon");

        //配置过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/error1", "anon");
        filterChainDefinitionMap.put("/kickout", "anon");

        //开放druid
        filterChainDefinitionMap.put("/druid/**", "anon");

        //开放swagger
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");

        filterChainDefinitionMap.put("/api/**","anon");

        filterChainDefinitionMap.put("/api2/**","anon");

        filterChainDefinitionMap.put("/*","anon");
        //加载数据库中配置的资源权限列表
        List<SysResource> resourcesList = sysResourceService.listUrlAndPermission();
        int a = 0;
        for (SysResource resource : resourcesList) {
            if (StringUtils.isNotBlank(resource.getUrl()) && StringUtils.isNotBlank(resource.getPermission())) {
                String permission = "perms[" + resource.getPermission() + "]";
                filterChainDefinitionMap.put(resource.getUrl(), permission);
                a += 1;
            }
        }

        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,这里我使用user操作即可，如果安全要求比较高，建议使用authc
        filterChainDefinitionMap.put("/**", "user");

      //  log.info("[hope-boot初始化资源成功,数据库资源条数]-[{}],初始化数据库资源条数-[{}]", resourcesList.size(), a);
        return filterChainDefinitionMap;
    }

    /***
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter abstractShiroFilter = null;
            try {
                abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("Get AbstractShiroFilter error");
            }
            PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
            //清空老的权限控制
            defaultFilterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            //重新构建生成
            Map<String, String> map = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> stringEntry : map.entrySet()) {
                String url = stringEntry.getKey();
                String chainDefinition = stringEntry.getValue().trim().replace(" ", "");
                defaultFilterChainManager.createChain(url, chainDefinition);
            }
        }
        log.info("[hope权限重新加载成功,低调小熊猫博客：https://aodeng.cc]");
    }

    /***
     * 重新加载用户权限
     * @param user
     */
    @Override
    public void reloadAuthorizingByUserId(SysUser user) {
        RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        HopeShiroRealm hopeShiroReam = (HopeShiroRealm) realmSecurityManager.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(user, realmName);
        subject.runAs(simplePrincipalCollection);
        hopeShiroReam.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();
        log.info("[以下用户权限更新成功！]-[{}]", user.getUsername());
    }

    /***
     * 重新加载所有拥有roleId角色的用户权限
     * @param roleId
     */
    @Override
    public void reloadAuthorizingByRoleId(Integer roleId) {
        List<SysUser> userList = sysUserService.listUsersByRoleId(roleId);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (SysUser user : userList) {
            reloadAuthorizingByUserId(user);
        }
    }
}
