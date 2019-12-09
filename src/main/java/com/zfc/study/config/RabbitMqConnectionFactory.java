package com.zfc.study.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-06 10:57
 * @T: RabbitMqConnectionFactory
 **/
@Configuration
public class RabbitMqConnectionFactory {

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
       try{

           factory.setUri("127.0.0.1");
           factory.setPort(5672);
           factory.setUsername("root");
           factory.setPassword("root");
           factory.setVirtualHost("zfc");
           ExecutorService service = Executors.newFixedThreadPool(1);
           factory.setSharedExecutor(service);
           factory.setConnectionTimeout(30000);
           factory.setAutomaticRecoveryEnabled(true);
           factory.setTopologyRecoveryEnabled(true);
           factory.setNetworkRecoveryInterval(10000);
           factory.setRequestedHeartbeat(360);

       }catch (Exception e){

       }



        return factory;
    }
}
