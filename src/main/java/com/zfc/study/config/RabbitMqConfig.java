package com.zfc.study.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-06 9:28
 * @T: RabbitConfig
 **/
@Configuration
public class RabbitMqConfig {

    @Bean
    DirectExchange TestDirectExchange(){
        //return new DirectExchange("TestDirectExchange");
        return (DirectExchange)ExchangeBuilder.directExchange("TestDirectExchange")
                .durable(true)//持久化
                .build();
    }

    @Bean
    public Queue TestDirectQueue(){
        return new Queue("TestDirectQueue",true);
    }

    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(TestDirectQueue())
                .to(TestDirectExchange())
                .with("TestDirectRouting");
    }

}
