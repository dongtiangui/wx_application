package com.spring.Controller;
import com.spring.Service.impl.LoginImpl;
import com.spring.Service.impl.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
