package com.midea.controller;

import com.midea.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @RequestMapping("/hello")
    public String hello(String name){
      return ribbonService.helloService(name);
    }


    @RequestMapping("/hello2")
    public String hello2(String name){
        return ribbonService.helloService(name);
    }
}
