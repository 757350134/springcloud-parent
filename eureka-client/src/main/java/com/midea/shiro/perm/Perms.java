package com.midea.shiro.perm;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;


@Component("perms")
public class Perms {
    //代码参考：https://gitee.com/supperzh/zb-shiro/blob/master/src/main/java/com/nbclass/shiro/PermsService.java
    public boolean hasPerm(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}
