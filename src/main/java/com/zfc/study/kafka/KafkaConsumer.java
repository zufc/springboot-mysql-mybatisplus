package com.zfc.study.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-06 11:34
 * @T: KafkaConsumer
 **/
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic")
    public void onMessage(String message){
        System.out.println(message);
    }

}
