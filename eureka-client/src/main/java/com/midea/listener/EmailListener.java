package com.midea.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2020/9/3 10:24
 * @Modified By：
 */
@Component
public class EmailListener implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        Object source = userEvent.getSource();

        System.out.println("send email!!!");
    }
}
