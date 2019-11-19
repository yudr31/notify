package com.spring.boot.notify.consumer;

import com.spring.boot.common.constant.MqConstant;
import com.spring.boot.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuderen
 * @version 2019/8/15 15:42
 */
@Slf4j
@Component
@RabbitListener(queues = MqConstant.DINGDING_MESSAGE_NOTIFY_KEY)
public class DingdingMessageConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${web_hook}")
    String webHook = "";

    @RabbitHandler
    public void process(String msg){
        log.info("DINGDING通知：{}", msg);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity(initDingdingMsg(msg), headers);
        ResponseEntity<String> result = restTemplate.postForEntity(webHook, request, String.class);
        log.info("dingding notify result: {}", result);
    }

    private Map<String, Object> initDingdingMsg(String msg){
        Map<String, Object> msgMap = JsonUtil.toMap(msg);
        Map<String, Object> result = new HashMap();
        result.put("msgtype", "text");
        Map<String, String> context = new HashMap();
        context.put("content", msgMap.get("msg").toString());
        result.put("text", context);
        return result;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if(httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
                break;
            }
        }
        return restTemplate;
    }

}
