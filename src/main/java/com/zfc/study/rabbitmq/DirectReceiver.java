package com.zfc.study.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-12-06 10:45
 * @T: DirectReceiver
 **/
public class DirectReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{



            String msg = message.toString();
            String[] msgArray = msg.split("'");

            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();
        }


    }
}
