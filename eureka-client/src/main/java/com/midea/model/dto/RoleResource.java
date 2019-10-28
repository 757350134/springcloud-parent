package com.midea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midea.model.SysRoleResource;

/**

 **/
public class RoleResource {
    /**
     * ---------------业务对象---------------
     **/
    private SysRoleResource sysRoleResource;

    public RoleResource() {
        this.sysRoleResource = new SysRoleResource();
    }

    public RoleResource(SysRoleResource sysRoleResource) {
        this.sysRoleResource = sysRoleResource;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysRoleResource getSysRoleResource() {
        return this.sysRoleResource;
    }

    public String getRoleId() {
        return this.sysRoleResource.getRoleId();
    }

    public void setRoleId(String roleId) {
        this.sysRoleResource.setRoleId(roleId);
    }

    public String getResourceId() {
        return this.sysRoleResource.getResourceId();
    }

    public void setResourceId(String resourceId) {
        this.sysRoleResource.setResourceId(resourceId);
    }

    public Integer getId() {
        return this.sysRoleResource.getId();
    }

    public void setId(Integer id) {
        this.sysRoleResource.setId(id);
    }

}
