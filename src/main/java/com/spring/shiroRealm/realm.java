package com.spring.shiroRealm;

import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Mappers.UserInterface;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

public class realm extends AuthorizingRealm {

    @Autowired
    private UserInterface userInterface;
    @Autowired
    @Qualifier(value = "redisTemplateLocal")
    private RedisTemplate redisTemplate;
    @Override
    public String getName() {
        return "realm";
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = principalCollection.toString();
        UserEntiry byUser = userInterface.getUserByUser(name);
        Set<String> set = new HashSet<>();
        set.add(byUser.getRoles());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(set);
        return info;
    }
    public Map<String,Object> getUsernamePassword(String name){
        Object username = redisTemplate.opsForValue().get("name");
        Object password = redisTemplate.opsForValue().get("password");
        Map<String,Object> map = new HashMap<>();
        if (username.toString()==null&&password.toString()==null){
            UserEntiry e2 = userInterface.getUserByUser(name);
            redisTemplate.opsForValue().set("name",e2.getName());
            redisTemplate.opsForValue().set("password",e2.getPassword());
            map.put("name",username.toString());
            map.put("password",password.toString());
            return map; 
        }
        map.put("name",username.toString());
        map.put("password",password.toString());
        return map;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) Token;
        String name = (String) token.getPrincipal();
        UserEntiry e2 = userInterface.getUserByUser(name);
//        Map<String, Object> usernamePassword = getUsernamePassword(name);
        try {
            if (e2.getName()!=null&&e2.getPassword()!=null){
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(e2.getName(),e2.getPassword(),getName());
                return info;
            }
        }catch (Exception e1){
            e1.printStackTrace();
            throw new AuthenticationException();
        }
        return null;
    }
}
