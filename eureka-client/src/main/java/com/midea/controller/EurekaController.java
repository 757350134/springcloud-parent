package com.midea.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.midea.model.MessageParam;
import com.midea.model.User;
import com.midea.service.UserService;
import com.midea.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Api(tags = "EurekaController")
public class EurekaController {

    @Autowired
    private RedisUtils redisUtils;

    private static final String key="bgy:iot:test:";


    @GetMapping("/getMessage")
    @ApiOperation("测试接口")
    public String getMessgae(){
        return "ok";
    }


    @PostMapping("/getParam")
    @ApiOperation("测试接口11")
    public String getMessgaeParam(@RequestBody @Valid MessageParam param){
        return param.getParam();
    }


    @GetMapping("/setValue")
    public  String setValue(){
        redisUtils.opsForValue(key+"age",18);
        int age = (int) redisUtils.getForValue(key+"age");
        Set<Object> name = redisUtils.opsForSet("name");
        System.out.println(age);
        return "success";
    }

    @GetMapping("/setValue2")
    public  String setValue2(){
        Map<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","全自动炒菜机");
        map.put("isNew",true);
        redisUtils.hset("item",map);
        String name = (String) redisUtils.hget("item", "name");

        Map<Object, Object> item = redisUtils.hmget("item");
        System.out.println(item.toString());


        List<Integer> list = Arrays.asList(1, 2, 3, 45, 33, 89);

        redisUtils.pushOpsForList("lscore",list);

        List<Object> lscore = redisUtils.opsForList("lscore", 0, -1);

        lscore.forEach(System.out::println);

//存储用户得分记录
        Map<String, Double> userScore = new HashMap<>();

        //创建10条用户得分记录并依次插入,i同时作为用户标识和分数
        for(double i=1;i<11;i++){
            //使用JedisPool获取jedis对象,因为JedisPool是线程安全的
            userScore.put(i+"", i);
        }

        redisUtils.opsForSet("userScore",userScore);
        return name;
    }
    @GetMapping("/setValue3")
    public  String setValue3(){
       redisUtils.zadd("userScore","A",1);
       redisUtils.zadd("userScore","B",4);

        redisUtils.zadd("userScore","C",8);
        redisUtils.zadd("userScore","D",6);

        Set userScore = redisUtils.zrange("userScore", 0, 2);

        for (Object o : userScore) {
            System.out.println(o);
        }

        return "success";
    }

    @GetMapping("/setValue4")
    public  String setValue4(){
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
