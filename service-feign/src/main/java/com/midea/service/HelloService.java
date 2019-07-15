package com.midea.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(value = "eureka-client")
public interface HelloService {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHi(String name);


    @RequestMapping(value = "/getUserById",method = RequestMethod.GET)
    String getUser();

    @RequestMapping(value = "/getDate",method = RequestMethod.GET)
    Date getDate();

    @RequestMapping(value = "/queryUser",method = RequestMethod.GET)
    String queryUser();


}
