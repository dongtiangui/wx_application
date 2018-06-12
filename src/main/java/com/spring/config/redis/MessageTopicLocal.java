//package com.spring.config.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.Topic;
//import org.springframework.stereotype.Component;
//
//@Component(value = "MessageTopicLocal")
//public class MessageTopicLocal extends ChannelTopic {
//
//
//    private String name;
//
//    public MessageTopicLocal() {
//        this("catha");
//    }
//
//    public MessageTopicLocal(String name) {
//        super(name);
//        this.name = name;
//    }
//
//    @Override
//    public String getTopic() {
//        return new MessageTopicLocal().name;
//    }
//}
