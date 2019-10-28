package com.midea.model;

import com.midea.config.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * 角色数据对象类

 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色数据对象类")
public class SysRole extends CommonEntity {
    /**
     * 扩展的id
     **/
    @ApiModelProperty(value = "扩展的id", name = "roleId",required = true)
    @Column(name = "roleId")
    private String roleId;
    /**
     * 角色名称
     **/
    @ApiModelProperty(value = "角色名称", name = "role",required = true)
    private String role;
    /**
     * 角色描述
     **/
    @ApiModelProperty(value = "角色描述", name = "description")
    private String description;
    /**
     * 是否可用：1有效2删除
     **/
    @ApiModelProperty(value = "是否可用：1有效2删除", name = "status",required = true)
    private Integer status;
    /**
     * 是否选中
     **/
    @Transient
    private Integer selected;
}
