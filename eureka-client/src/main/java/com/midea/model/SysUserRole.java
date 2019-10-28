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
public class SysUserRole {

    private static final long serialVersionUID = 2133462812918890038L;
    /**
     * 编号，主键，资源表
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     **/
    @Column(name = "userId")
    private String userId;
    /**
     * 角色id
     **/
    @Column(name = "roleId")
    private String roleId;

}