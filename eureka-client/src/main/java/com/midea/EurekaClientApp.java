package com.midea;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.midea.mapper")
@RestController
@Api(tags = "EurekaClientApp")
public class EurekaClientApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApp.class);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "wxp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "wxp") String name) {
        return "hello  " + name + " ,i am from port:" + port;
    }
}
