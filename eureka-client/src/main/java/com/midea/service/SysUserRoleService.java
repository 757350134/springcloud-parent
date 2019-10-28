package com.midea.service;

import com.midea.config.BaseService;
import com.midea.model.SysUserRole;

/**

 **/
public interface SysUserRoleService extends BaseService<SysUserRole> {
    /***
     * 添加用户角色
     * @param userId
     * @param roleIds
     */
    void addUserRole(Integer userId, String roleIds);
}
