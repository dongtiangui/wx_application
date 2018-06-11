package com.spring.redis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public class HashRedis {

    public RedisTemplate redisTemplateLocal;

    @Autowired(required = false)
    public HashRedis(@Qualifier(value = "redisTemplateLocal") RedisTemplate redisTemplateLocal) {
        this.redisTemplateLocal = redisTemplateLocal;
    }

    @JsonSerialize
    private String date;
    @Cacheable(value = "cacheManagerLocal")
    public Set insert(){
        date = LocalDate.now().toString();
        UserEntiry userEntiry = new UserEntiry();
        userEntiry.setId(80);
        userEntiry.setName("dong");
        userEntiry.setPassword("789456");
        userEntiry.setTime_db(date);
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                redisOperations.boundSetOps("multiEntiry").add(userEntiry.toString());
                System.out.println("redisOperations.opsForSet().members(\"multi\") = " + redisOperations.opsForSet().members("multiEntiry"));

                List list = redisOperations.exec();

                for (Object o: list) {
                    System.out.println("o = " + o);
                }
                Set value = redisOperations.opsForSet().members("multiEntiry");

                return value;
            }
        };
        Long log = System.currentTimeMillis();
        Set list = (Set) redisTemplateLocal.execute(sessionCallback);

        Long log1 = System.currentTimeMillis();
        System.out.println(log1-log);
        
        return list;
    }

}
