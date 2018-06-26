package com.spring.Controller;
import com.spring.Service.impl.LoginImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticatorSuccess {

    private final LoginImpl login;

    @Autowired
    public AuthenticatorSuccess(LoginImpl login) {
        this.login = login;
    }

    @RequestMapping(value = "/admin")
    @RequiresPermissions(value = "admin")
    public String admin(@RequestParam("name") String name,ModelMap map){
        map.addAttribute("nameUser",name);
        return "admin";
    }
    @RequiresPermissions(value = "student")
    @RequestMapping(value = "/student")
    public String student(@RequestParam(value = "name") String name, ModelMap map){

        map.addAttribute("nameUser",name);

        return "student";
    }
}
