package com.midea.service.impl;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.midea.config.BaseServiceImpl;
import com.midea.mapper.SysRoleMapper;
import com.midea.mapper.SysRoleResourceMapper;
import com.midea.model.SysRole;
import com.midea.model.SysRoleResource;
import com.midea.model.base.ResponseVo;
import com.midea.model.dto.Role;
import com.midea.model.vo.RoleConditionVo;
import com.midea.service.SysRoleService;
import com.midea.utils.ResultHopeUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**

 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysRoleResourceMapper roleResourceMapper;

    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper, SysRoleResourceMapper roleResourceMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.roleResourceMapper = roleResourceMapper;
    }

    /***
     * 数据类型转换
     * @param sysRoles
     * @return
     */
    private List<Role> getRoles(List<SysRole> sysRoles) {
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        List<Role> roleList = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            roleList.add(new Role(sysRole));
        }
        return roleList;
    }

    @Override
    public List<SysRole> listRolesByUserId(Integer userId) {
        return sysRoleMapper.listRolesByUserId(userId);
    }

    @Override
    public PageInfo<SysRole> findPageBreakByCondition(RoleConditionVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysRole> sysRoles = sysRoleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        PageInfo list = new PageInfo<>(sysRoles);
        return list;
    }

    @Override
    public Set<String> findRoleByUserId(Integer userId) {
        return sysRoleMapper.findRoleByUserId(userId);
    }

    @Override
    public ResponseVo addAssignResourceById(String roleId, List<String> resourceIds) {
        try {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResourceMapper.delete(roleResource);
            for (String resourceId : resourceIds) {
                roleResource.setId(null);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
            return ResultHopeUtil.success("分配资源成功！");
        } catch (Exception e) {
            return ResultHopeUtil.error("分配资源失败！");
        }
    }

    @Override
    public List<Map<String, Object>> RoleListWithSelected(Integer userId) {
        List<SysRole> userRoleList = sysRoleMapper.RoleListWithSelected(userId);
        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysRole role : userRoleList) {
            map = new HashMap<String, Object>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", role.getSelected() != null && role.getSelected() == 1);
            map.put("name", role.getRole());
            mapList.add(map);
        }
        return mapList;
    }
}
