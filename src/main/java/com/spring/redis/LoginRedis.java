package com.spring.redis;


import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginRedis {

    private final RedisTemplate redisTemplate;

    @Autowired
    public LoginRedis(@Qualifier(value = "redisTemplateLocal") RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void getLoginUser(String name,String password){


        redisTemplate.opsForValue().set("name",name);

        redisTemplate.opsForValue().set("pass",password);

      //return null;

    }

    public LoginUser setUser(){

        String name = (String) redisTemplate.opsForValue().get("name");

        String pass = (String) redisTemplate.opsForValue().get("pass");

        LoginUser loginUser = new LoginUser();

        loginUser.setName(name);
        loginUser.setPassword(pass);

        return loginUser;


    }


}
