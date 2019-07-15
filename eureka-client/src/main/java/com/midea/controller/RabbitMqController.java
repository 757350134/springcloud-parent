package com.midea.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "RabbitMqController")
public class RabbitMqController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/setMessage")
    @ApiOperation("setMessage")
    public String getMessgae(){

        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        return "ok";
    }

}
