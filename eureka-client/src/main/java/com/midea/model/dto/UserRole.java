package com.midea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midea.model.SysUserRole;

/**
 * @program:hope-boot

 **/
public class UserRole {
    /**
     * ---------------业务对象---------------
     **/
    private SysUserRole sysUserRole;

    public UserRole() {
        this.sysUserRole = new SysUserRole();
    }

    public UserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysUserRole getSysUserRole() {
        return this.sysUserRole;
    }

    public String getUserId() {
        return this.sysUserRole.getUserId();
    }

    public void setUserId(String userId) {
        this.sysUserRole.setUserId(userId);
    }

    public String getRoleId() {
        return this.sysUserRole.getRoleId();
    }

    public void setRoleId(String roleId) {
        this.sysUserRole.setRoleId(roleId);
    }

    public Integer getId() {
        return this.sysUserRole.getId();
    }

    public void setId(Integer id) {
        this.sysUserRole.setId(id);
    }

}
