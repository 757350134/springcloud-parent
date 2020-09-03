package com.midea.controller;

import com.midea.listener.UserEvent;
import com.midea.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2020/9/3 10:28
 * @Modified By：
 */
@RestController
@RequestMapping("userListenerController")
public class UserListenerController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/loginListener")
    @ResponseBody
    public String login(@RequestBody User user){
        applicationContext.publishEvent(new UserEvent(user));
        return "success";
    }
}
