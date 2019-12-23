package com.wxp;

import com.wxp.base.MessageCode;
import com.wxp.base.Result;
import com.wxp.base.ResultUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SpringInitApplication {
    public static void main(String[] args) {
       // System.out.println(111);
        SpringApplication.run(SpringInitApplication.class);
    }

    @RequestMapping("/getMessage")
    public Result  getMessage(){
        return ResultUtil.success("ok");
    }



    @RequestMapping("/test")
    public Result  test(){
        log.debug("master");
        return ResultUtil.success("test");
    }

    @RequestMapping("/getError")
    public Result  getError(){
        return ResultUtil.error(MessageCode.SERVER_EXCEPTION);
    }


}
