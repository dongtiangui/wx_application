package com.spring.Controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class auth {


    @RequestMapping(value = "/admin")
//    @RequiresPermissions(value = "admin")
    public String admin(){


        return "admin";

    }

    @RequestMapping(value = "/student")
    public String student(){



        return "student";

    }


}
