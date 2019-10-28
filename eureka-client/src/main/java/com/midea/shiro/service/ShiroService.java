package com.midea.shiro.service;


import com.midea.model.SysUser;

import java.util.Map;


public interface ShiroService {
    /***
     * 初始化权限
     * @return
     */
    Map<String, String> loadFilterChainDefinitions();

    /***
     * 重新加载权限
     */
    void updatePermission();

    /***
     * 重新加载用户权限
     * @param user
     */
    void reloadAuthorizingByUserId(SysUser user);

    /***
     * 重新加载所有拥有roleId角色的用户权限
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Integer roleId);
}