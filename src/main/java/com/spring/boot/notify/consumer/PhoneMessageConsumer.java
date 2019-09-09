package com.spring.boot.notify.consumer;

import com.spring.boot.common.constant.MqConstant;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuderen
 * @version 2019/8/15 15:42
 */
@Component
@RabbitListener(queues = MqConstant.PHONE_MESSAGE_NOTIFY_KEY)
public class PhoneMessageConsumer extends PhoneNotifyProcesser{

    @RabbitHandler
    public void process(String msg){
        System.out.println("短信通知：" + msg);
        super.process(msg);
    }

    public void doNotify(PhoneNumber from, PhoneNumber to, String msg){
        Message message = Message.creator(to, from, msg).create();
        System.out.println(message.getSid());
    }

}
