package com.spring.Controller;
import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Service.impl.LoginImpl;
import com.spring.Service.impl.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/form")
public class ajaxController {

    public final LoginImpl login;
    public final Service service;
    @Autowired
    public ajaxController(LoginImpl login, Service service) {
        this.login = login;
        this.service = service;
    }

    @RequestMapping(value = "/ajax",method = RequestMethod.GET)
    public Map<String,Object> getAjax(){
        Map<String,Object> map = new HashMap<>();
//        if (login.ajax(user.getName(),user.getPassword())){
//            map.put("resultAjax",true);
//            return map;
//        }
//        else {
//            map.put("resultAjax",false);
//        }
        map.put("User",service.getUserByUserEntiry("admin").getName());

        return map;
    }

}
