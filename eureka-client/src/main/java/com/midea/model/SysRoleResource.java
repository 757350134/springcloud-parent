package com.midea.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**

 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResource {
    private static final long serialVersionUID = 2702192486029856480L;
    /**
     * 编号，主键，资源表
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色id
     **/
    @Column(name = "roleId")
    private String roleId;
    /**
     * 资源id
     **/
    @Column(name = "resourceId")
    private String resourceId;

}