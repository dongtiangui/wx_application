package com.spring.Controller;
import com.spring.Service.impl.Service;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private final Service service;
    @Autowired
    public LoginController(Service service) {
        this.service = service;
    }
    @RequestMapping(value = "/Login")
    public String formController(String ID, String name,String pass, ModelMap map){

        UsernamePasswordToken Token = new UsernamePasswordToken(name,pass);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("Token = " + Token.getUsername());
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated){
            try {
                subject.login(Token);
                return "success";
            }catch (Exception e) {
                map.addAttribute("message","认证失败");
                return "index";
            }
        }
        return "index";
    }
}
