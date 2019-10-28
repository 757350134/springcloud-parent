package com.midea.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.midea.model.SysRole;

import java.util.Date;

/**
 * 角色业务对象类

 **/
public class Role {
    /**
     * ---------------业务对象---------------
     **/
    private SysRole sysRole;

    public Role() {
        this.sysRole = new SysRole();
    }

    public Role(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysRole getSysRole() {
        return this.sysRole;
    }

    public String getRoleUuid() {
        return this.sysRole.getRoleId();
    }

    public void setRoleUuid(String roleUuid) {
        this.sysRole.setRoleId(roleUuid);
    }

    public String getRole() {
        return this.sysRole.getRole();
    }

    public void setRole(String role) {
        this.sysRole.setRole(role);
    }

    public String getDescription() {
        return this.sysRole.getDescription();
    }

    public void setDescription(String description) {
        this.sysRole.setDescription(description);
    }

    public Integer getStatus() {
        return this.sysRole.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysRole.setStatus(status);
    }

    public Integer getId() {
        return this.sysRole.getId();
    }

    public void setId(Integer id) {
        this.sysRole.setId(id);
    }

    public Date getCreatetime() {
        return this.sysRole.getCreatetime();
    }

    public void setCreatetime(Date createtime) {
        this.sysRole.setCreatetime(createtime);
    }

    public Date getUpdatetime() {
        return this.sysRole.getUpdatetime();
    }

    public void setUpdatetime(Date updatetime) {
        this.sysRole.setUpdatetime(updatetime);
    }
}
