package com.midea.model.vo;

import com.midea.model.SysResource;
import com.midea.model.base.BaseConditionVo;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVo extends BaseConditionVo {
    private SysResource resource;
    private String type;
}
