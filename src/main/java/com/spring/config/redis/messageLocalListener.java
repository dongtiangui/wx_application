//package com.spring.config.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.io.UnsupportedEncodingException;
//
//@Component(value = "messageLocalListener")
//public class messageLocalListener implements MessageListener {
//
//    private  RedisTemplate template;
//    @Autowired
//    public messageLocalListener(@Qualifier(value = "redisTemplateLocal") RedisTemplate template) {
//
//        this.template = template;
//    }
//
//    public messageLocalListener(){
//
//    }
//    public RedisTemplate getTemplate() {
//        return template;
//    }
//
//    @Override
//    public void onMessage(Message message, byte[] bytes) {
//
//        byte[] body = message.getBody();
//        byte[] channel = message.getChannel();
//        try {
//            String name = new String(body,"UTF-8");
//            String pass = new String(channel,"UTF-8");
//            System.out.println("pass = " + pass);
//            System.out.println("name = " + name);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//}
