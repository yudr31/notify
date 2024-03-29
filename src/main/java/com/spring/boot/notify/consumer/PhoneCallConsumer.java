package com.spring.boot.notify.consumer;

import com.spring.boot.common.constant.MqConstant;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author yuderen
 * @version 2019/8/15 15:42
 */
@Slf4j
@Component
@RabbitListener(queues = MqConstant.PHONE_CALL_NOTIFY_KEY)
public class PhoneCallConsumer extends PhoneNotifyProcesser{

    @RabbitHandler
    public void process(String msg){
        log.info("电话通知：{}", msg);
        super.process(msg);
    }


    public void doNotify(PhoneNumber from, PhoneNumber to, String msg){
        try {
            URI uri = URI.create("http://demo.twilio.com/docs/voice.xml");
            Call call = Call.creator(to, from, uri).create();
            log.info("电话通知成功：{}", call.getSid());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
