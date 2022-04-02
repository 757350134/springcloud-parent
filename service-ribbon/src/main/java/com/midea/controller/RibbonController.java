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


    @RequestMapping("/hello1")
    public String hello1(String name){
        return ribbonService.helloService(name);
    }
}
