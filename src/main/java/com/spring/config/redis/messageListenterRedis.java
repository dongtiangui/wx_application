package com.spring.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class messageListenterRedis {

    private final JedisConnectionFactory jedisConnectionFactory;
    private final MessageTopicLocal messageTopicLocal;
    private final messageLocalListener messageLocalListener;

    @Autowired
    public messageListenterRedis(@Qualifier(value = "jedisConnectionFactoryLocal") JedisConnectionFactory jedisConnectionFactory, @Qualifier(value = "MessageTopicLocal") MessageTopicLocal messageTopicLocal, @Qualifier(value = "messageLocalListener") messageLocalListener messageLocalListener) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.messageTopicLocal = messageTopicLocal;
        this.messageLocalListener = messageLocalListener;
    }

    @Bean
    public MessageListenerAdapter adapter(){

        return new MessageListenerAdapter(new messageLocalListener());
    }

    @Bean(name = "redisMessageListenerContainer" )
    public RedisMessageListenerContainer redisMessageListenerContainer(JedisConnectionFactory redisConnectionFactory, MessageListenerAdapter adapter){
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
//        List<MessageTopicLocal> list = new ArrayList<>();
//        list.add(messageTopicLocal);
//        Map map = new HashMap();
//        map.put(messageLocalListener,list);
        listenerContainer.setConnectionFactory(redisConnectionFactory);
        listenerContainer.setTaskExecutor(threadPoolTaskExecutor());
        listenerContainer.addMessageListener(adapter,new PatternTopic("news.*"));
        return listenerContainer;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(20);
        poolTaskExecutor.setMaxPoolSize(50);
        poolTaskExecutor.setAllowCoreThreadTimeOut(false);
        return poolTaskExecutor;

    }

}
