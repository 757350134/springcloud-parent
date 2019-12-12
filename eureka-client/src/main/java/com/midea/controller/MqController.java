package com.midea.controller;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/10/28 11:00
 * @Modified By：
 */

import com.midea.config.MqttConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 **/
@Api(value = "mq", description = "mq api", position = 40, produces = "http")
@RestController("/mqController")
@Slf4j
public class MqController {

    @Autowired
    private MqttClient client;

    @GetMapping("/send")
    @ApiOperation("send")
    public String getMessgae(){

        String msg = "{\"OrgPt\":{\"Order\":0,\"Angle\":180,\"X\":20000,\"WorkState\":0,\"Y\":6595,\"Z\":0},\"RoutePts\":[{\"Order\":1,\"Angle\":180,\"X\":19175,\"WorkState\":1,\"Y\":6595,\"Z\":0,\"WorkData\":[]},{\"Order\":2,\"Angle\":180,\"X\":19175,\"WorkState\":1,\"Y\":5995,\"Z\":0,\"WorkData\":[]},{\"Order\":3,\"Angle\":180,\"X\":19175,\"WorkState\":1,\"Y\":5395,\"Z\":0,\"WorkData\":[]},{\"Order\":4,\"Angle\":180,\"X\":20645,\"WorkState\":0,\"Y\":5395,\"Z\":0,\"WorkData\":[]},{\"Order\":5,\"Angle\":90,\"X\":20645,\"WorkState\":0,\"Y\":5395,\"Z\":0,\"WorkData\":[]},{\"Order\":6,\"Angle\":90,\"X\":20645,\"WorkState\":1,\"Y\":6820,\"Z\":0,\"WorkData\":[]},{\"Order\":7,\"Angle\":90,\"X\":21245,\"WorkState\":1,\"Y\":6820,\"Z\":0,\"WorkData\":[]}],\"NumOfPoints\":7}";
        MqttMessage message = new MqttMessage(msg.getBytes());
        //MqttClient client = mqttConfig.getClient();
        try {
            client.publish("/route/w123/info",message);
        } catch (Exception e) {
            log.error("error==="+e);

        }
        return "ok";
    }
}
