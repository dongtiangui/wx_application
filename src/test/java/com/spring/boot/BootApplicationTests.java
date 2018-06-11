package com.spring.boot;

import javafx.concurrent.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.concurrent.FutureTask;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class BootApplicationTests {

    @Test
    public void contextLoads() {
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (Object o : list) {
            System.out.println("o = " + o);
        }
        
    }

}
