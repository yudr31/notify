package com.spring.boot.notify.consumer;

import com.spring.boot.common.constant.MqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuderen
 * @version 2019/8/15 15:42
 */
@Component
@RabbitListener(queues = MqConstant.DINGDING_MESSAGE_NOTIFY_KEY)
public class DingdingMessageConsumer {

    @RabbitHandler
    public void process(String msg){
        System.out.println("DINGDING通知：" + msg);
    }

}
