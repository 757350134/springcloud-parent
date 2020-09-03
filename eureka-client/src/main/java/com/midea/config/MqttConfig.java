package com.midea.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @Author: wxp
 * @Description:
 * @Date:Create：in 2019/10/28 10:30
 * @Modified By：
 */

//@Configuration
@Slf4j
public class MqttConfig {

    private static final long CLIENT_TIME_OUT = 10000L;
    //连接服务器是否清空客户端session
    private static final boolean SESSION_CLEAN = false;

    //是否自动重连，定时任务重连
    private static final boolean AUTO_RECONNECT = false;


    //连接超时时间
    private static final int CONN_TIME_OUT = 10;

    //会话心跳
    private static final int DETECT_TIME = 20;
    private  String pubBrokerAttrValue="tcp://127.0.0.1:1883";

    private  String clienId= UUID.randomUUID().toString().replace("-","");
    private String pubUserNameValue="admin";
    private String pubPasswordValue="admin";

    private String topic="/route/w123/info";
    @Bean
    public MqttClient  getClient(){
        //初始化客户端
        MqttClient  mqttPublisher = null;
        try {
            mqttPublisher = new MqttClient(pubBrokerAttrValue, clienId, new MemoryPersistence());

            //设置mqtt客户端超时时间
            mqttPublisher.setTimeToWait(CLIENT_TIME_OUT);

            // 设置回调函数
            mqttPublisher.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(final Throwable cause) {
                    log.warn("{} connectionLost,", pubBrokerAttrValue);
                    log.error("Cause:", cause.toString());
                }

                @Override
                public void deliveryComplete(final IMqttDeliveryToken token) {
                    log.info("{} deliveryComplete--------- {} ", pubBrokerAttrValue, token.isComplete());
                }

                @Override
                public void messageArrived(final String topic, final MqttMessage message) throws Exception {
                    log.info("Topic: {} Qos: {} message content: {}", topic, message.getQos(),
                            new String(message.getPayload()));

                    log.info("{} messageArrived---------", pubBrokerAttrValue);
                }
            });
            //获取连接参数
            MqttConnectOptions mqttOption = getMQTTOption();

            // 建立连接
            mqttPublisher.connect(mqttOption);
            log.debug("User {} connect broker {} success...", pubUserNameValue, pubUserNameValue);
        } catch (MqttException e) {
           log.error("e=========="+e);
        }

        try {
            mqttPublisher.subscribe(topic,2);
        } catch (Exception e) {
            log.error("消费异常。。。",e);
        }

        return  mqttPublisher;
    }

    private MqttConnectOptions getMQTTOption() throws MqttException {
        // 创建链接参数
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 在重新启动和重新连接时记住状态
        connOpts.setCleanSession(SESSION_CLEAN);
        // 设置连接的用户名
        connOpts.setUserName(pubUserNameValue);
        connOpts.setPassword(pubPasswordValue.toCharArray());
        //设置自动重连
        connOpts.setAutomaticReconnect(AUTO_RECONNECT);
        // 设置超时时间 单位为秒
        connOpts.setConnectionTimeout(CONN_TIME_OUT);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        connOpts.setKeepAliveInterval(DETECT_TIME);

        return connOpts;
    }








    //@Bean
    public void Sub(){
        MqttClient client = getClient();
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                log.info("recivice a mess,{},{}",s,mqttMessage.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        try {
            client.subscribe(topic,2);
        } catch (MqttException e) {
            log.error("消费异常。。。",e);
        }
    }









    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
