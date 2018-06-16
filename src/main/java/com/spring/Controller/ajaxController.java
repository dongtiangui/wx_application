package com.spring.Controller;
import com.spring.EntiryPage.ControllerEntiry.LoginUser;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Service.impl.LoginImpl;
import com.spring.Service.impl.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ajaxController {

    public final LoginImpl login;
    public final Service service;
    @Autowired
    public ajaxController(LoginImpl login, Service service) {
        this.login = login;
        this.service = service;
    }

    @RequestMapping("/registerPage")
    public String register(){


        return "register";
    }
    @RequestMapping(value = "/revise")
    public String  revisePassword(){




        return "revise";
    }

}
