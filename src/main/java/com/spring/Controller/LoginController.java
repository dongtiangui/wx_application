package com.spring.Controller;
import com.spring.Service.impl.Service;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
@RequestMapping("/spring")
public class LoginController {
    private final Service service;

    @Autowired
    public LoginController(Service service) {
        this.service = service;
    }
    @RequestMapping(value = "/Login")
    @ResponseBody
    public ModelAndView formController(@RequestParam("name") String name,
                                       @RequestParam("pass") String pass,
                                       @RequestParam("ID") Integer Id){
        ModelAndView mv = new ModelAndView();
        System.out.println("name = " + name);
        System.out.println("password = " + pass);
        System.out.println("Id = " + Id);
        UsernamePasswordToken Token = new UsernamePasswordToken(name,pass);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("Token = " + Token.getUsername());
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated){
            try {
                subject.login(Token);
                mv.setView(new MappingJackson2JsonView());
                mv.addObject("success","success");
            }catch (UnknownAccountException e){
                System.out.println("e = " + e.getMessage());
                mv.addObject("error","error");
                return mv;
            }catch (IncorrectCredentialsException e){
                System.out.println("e = " + e.getMessage());
                mv.addObject("error","error");
                return mv;
            }
        }
        return mv;
    }
    @RequestMapping(value = "/errorPassword")
    
    public ModelAndView errorPackage(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("error","错误密码");
        mv.setViewName("index");
        return mv;
    }
}
