package com.midea.service;

import com.midea.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public interface CommonService {

    @GetMapping("/getDate")
    public Date getDate();

    @GetMapping("/queryUser")
    public User queryUser();
}
