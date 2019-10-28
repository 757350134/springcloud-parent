package com.midea.model.vo;

import com.midea.model.SysRole;
import com.midea.model.base.BaseConditionVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-25 10:33
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleConditionVo extends BaseConditionVo {
    private SysRole role;
}
