package com.zfc.study.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-06 10:08
 * @T: RabbitmqConsumer
 **/
@Component
@RabbitListener(queues = "TestDirectQueue")
public class RabbitmqConsumer {

    @RabbitHandler
    public void handleMessage(Map testMessage){

        System.out.println("接受消息："+testMessage.toString());

    }
}
