package com.midea.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2020/9/3 10:24
 * @Modified By：
 */

public class UserEvent extends ApplicationEvent {
    public UserEvent(Object source) {
        super(source);
    }
}
