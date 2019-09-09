package com.spring.boot.notify.consumer;

import com.spring.boot.common.util.JsonUtil;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * @author yuderen
 * @version 2019/8/15 18:07
 */
public abstract class PhoneNotifyProcesser implements NotifyProcesser {

    @Value("${account_sid}")
    String ACCOUNT_SID = "";
    @Value("${auth_token}")
    String AUTH_TOKEN = "";
    @Value("${from_phone}")
    String from_phone = "";

    @Override
    public void process(String msg) {
        Map<String, Object> phoneMap = JsonUtil.toMap(msg);
        String phone = phoneMap.get("phone").toString();
        String message = phoneMap.get("msg").toString();

        PhoneNumber from = new PhoneNumber(from_phone);
        PhoneNumber to = new PhoneNumber("+86" + phone);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        this.doNotify(from, to, message);
    }

    abstract void doNotify(PhoneNumber from, PhoneNumber to, String msg);
}
