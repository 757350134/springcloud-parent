package com.midea.listener;

import com.midea.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2020/9/3 10:26
 * @Modified By：
 */
@Component
public class MsgListener  implements ApplicationListener<UserEvent> {
    @Override
    public void onApplicationEvent(UserEvent userEvent) {
        User user = (User) userEvent.getSource();
        System.out.println(user.getName()+"Send msg!!!");
    }
}
