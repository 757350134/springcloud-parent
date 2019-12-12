package com.midea.timer;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/10/28 14:27
 * @Modified By：
 */
@Slf4j
//@Component
public class MqTask {

    @Autowired
    private MqttClient client;

    @Scheduled(fixedDelay  = 3000)
    public void pipo(){
        boolean connected = client.isConnected();
        if(!connected){
            log.info("是否连接：{}",connected);
            try {
                client.reconnect();
            } catch (Exception e) {
                log.error(""+e);
            }
        }
    }
}
