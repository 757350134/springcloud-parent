package com.midea.controller;

import com.midea.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FeignController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hi")
    public String sayHi(String name){
        return  helloService.sayHi(name);
    }

    @GetMapping("/getUserById")
    public String getUser(){
        return  helloService.getUser();
    }

    @GetMapping("/getDate")
    public Date getDate(){
        return  helloService.getDate();
    }

    @GetMapping("/queryUser")
    public String queryUser(){
        return  helloService.queryUser();
    }
}
