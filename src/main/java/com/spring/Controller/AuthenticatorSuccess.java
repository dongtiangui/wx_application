package com.spring.Controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticatorSuccess {


    @RequestMapping(value = "/admin")
    @RequiresPermissions(value = "admin")
    public String admin(){


        return "admin";

    }
    @RequiresPermissions(value = "student")
    @RequestMapping(value = "/student")
    public String student(){


        return "student";

    }

}
