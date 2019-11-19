package com.spring.boot.notify.consumer;

import com.spring.boot.common.constant.MqConstant;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yuderen
 * @version 2019/8/15 15:42
 */
@Slf4j
@Component
@RabbitListener(queues = MqConstant.PHONE_MESSAGE_NOTIFY_KEY)
public class PhoneMessageConsumer extends PhoneNotifyProcesser{

    @RabbitHandler
    public void process(String msg){
        log.info("短信通知：{}", msg);
        super.process(msg);
    }

    public void doNotify(PhoneNumber from, PhoneNumber to, String msg){
        Message message = Message.creator(to, from, msg).create();
        log.info("短信通知：{}",message.getSid());
    }

}
