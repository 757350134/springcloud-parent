package com.midea.controller;

import com.midea.model.User;
import com.midea.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById")
    @ResponseBody
    public User getUserById() {
        User user = this.userService.queryById(6L);
        return user;
    }
    @GetMapping("/all")
    public String all(ModelMap model) {
        // 查询用户
        List<User> users = this.userService.queryAll();
        // 放入模型
        model.addAttribute("users", users);
        // 返回模板名称（就是classpath:/templates/目录下的html文件名）
        return "users";
    }
}
