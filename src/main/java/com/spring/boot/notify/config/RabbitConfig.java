package com.spring.boot.notify.config;

import com.spring.boot.common.constant.MqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuderen
 * @version 2019/8/15 13:53
 */
@Configuration
public class RabbitConfig {

    @Bean("phoneMessageQueue")
    public Queue initPhoneMessageQueue(){
        return new Queue(MqConstant.PHONE_MESSAGE_NOTIFY_KEY);
    }

    @Bean("phoneCallQueue")
    public Queue initPhoneCallQueue(){
        return new Queue(MqConstant.PHONE_CALL_NOTIFY_KEY);
    }

    @Bean("dingdingMessageQueue")
    public Queue initDingdingMessageQueue(){
        return new Queue(MqConstant.DINGDING_MESSAGE_NOTIFY_KEY);
    }

    @Bean("notifyExchange")
    public TopicExchange initNotifyExchange(){
        return new TopicExchange(MqConstant.NOTIFY_EXCHANGE);
    }

    @Bean
    public Binding phoneMessageBindingNotifyExchange(@Qualifier("phoneMessageQueue") Queue queue,
                                                     @Qualifier("notifyExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MqConstant.PHONE_MESSAGE_NOTIFY_KEY);
    }

    @Bean
    public Binding phoneCallBindingNotifyExchange(@Qualifier("phoneCallQueue") Queue queue,
                                                     @Qualifier("notifyExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MqConstant.PHONE_CALL_NOTIFY_KEY);
    }

    @Bean
    public Binding dingingMessageBindingNotifyExchange(@Qualifier("dingdingMessageQueue") Queue queue,
                                                  @Qualifier("notifyExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MqConstant.DINGDING_MESSAGE_NOTIFY_KEY);
    }

}
