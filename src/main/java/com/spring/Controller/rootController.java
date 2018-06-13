package com.spring.Controller;
import com.spring.redis.HashRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class rootController {

    private final RedisTemplate redisTemplate;
    private HashRedis redis;
    @Autowired
    public rootController(HashRedis redis,
                          @Qualifier(value = "redisTemplateLocal") RedisTemplate redisTemplate) {
        this.redis = redis;
        this.redisTemplate = redisTemplate;
    }
    @RequestMapping("/")

    public String BOOT(Model model){

        return "index";
    }
}
