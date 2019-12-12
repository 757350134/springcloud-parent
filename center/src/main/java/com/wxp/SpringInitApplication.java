package com.wxp;

import com.wxp.base.MessageCode;
import com.wxp.base.Result;
import com.wxp.base.ResultUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/10/30 17:29
 * @Modified By：
 */
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@RestController
public class SpringInitApplication {
    public static void main(String[] args) {
       // System.out.println(111);
        SpringApplication.run(SpringInitApplication.class);
    }

    @RequestMapping("/getMessage")
    public Result  getMessage(){
        System.out.println("ms12");
        return ResultUtil.success("ok");
    }



    @RequestMapping("/test")
    public Result  test(){
        System.out.println("test ms12");
        return ResultUtil.success("test");
    }

    @RequestMapping("/getError")
    public Result  getError(){
        return ResultUtil.error(MessageCode.SERVER_EXCEPTION);
    }


}
