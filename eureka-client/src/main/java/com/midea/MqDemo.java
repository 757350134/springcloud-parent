package com.midea;


import com.midea.utils.RedisUtils;
import com.netflix.discovery.shared.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MqDemo {

     /* @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }*/

    @Autowired
    private com.midea.config.CacheConfig CacheConfig;

     @Autowired
    private RedisUtils redisUtils;

     @Test
     public void testPut(){

         String value="message";

         redisUtils.opsForValue("m1",value);
     }
}
