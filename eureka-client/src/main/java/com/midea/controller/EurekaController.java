package com.midea.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.midea.model.User;
import com.midea.service.UserService;
import com.midea.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api(tags = "EurekaController")
public class EurekaController {

    @Autowired
    private RedisUtils redisUtils;


    @GetMapping("/getMessage")
    @ApiOperation("测试接口")
    public String getMessgae(){
        return "ok";
    }


    @GetMapping("/setValue")
    public  String setValue(){
        redisUtils.opsForValue("age",18);
        int age = (int) redisUtils.getForValue("age");
        Set<Object> name = redisUtils.opsForSet("name");
        System.out.println(age);
        return "success";
    }

    @GetMapping("/setValue1")
    public  String setValue1(){
        redisUtils.opsForValue("age",18);
        int age = (int) redisUtils.getForValue("age");
        Set<Object> name = redisUtils.opsForSet("name");
        System.out.println(age);
        return "success";
    }
    @GetMapping("/getList")
    public  String getList(){
        List<String> list=new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("xiaoyu");
        redisUtils.pushOpsForList("nameList",list);
        List<Object> nameList = redisUtils.opsForList("nameList", 0, -1);
        return JSONUtils.toJSONString(nameList);
    }
    @Autowired
    private UserService userService;

    @GetMapping("/getMap")
    public  String getMap(){
        Map<String,Object> map=new HashMap<String, Object>();
      /*  redisUtils.pushOpsForList("nameList",list);*/
        User user =userService.queryById(6L);

        redisUtils.hset("userInfo", (Map<String, Object>) JSON.toJSON(user));

        Map<Object, Object> userInfo = redisUtils.hmget("userInfo");

        System.out.println(redisUtils.hget("userInfo","name"));
        return JSONUtils.toJSONString(userInfo);
    }
}
