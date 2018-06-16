package com.spring.Controller;
import com.spring.Service.impl.LoginImpl;
import com.spring.Service.impl.Service;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final Service service;
    private final LoginImpl loginImpl;
    @Autowired
    public LoginController(Service service, LoginImpl loginImpl) {
        this.service = service;
        this.loginImpl = loginImpl;
    }
    @RequestMapping(value = "/Login")
    public String formController(String ID, String name,String pass,ModelMap map){
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

    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public ModelAndView regise(String name,String pass,String roles){
        ModelAndView mv = new ModelAndView();
        boolean insert = loginImpl.insert(name, pass,roles);
        if (insert){
            mv.setViewName("index");
            mv.addObject("success","注册成功");
        }
        return mv;
    }

    @RequestMapping(value = "/revisePassword",method = RequestMethod.POST)
    public ModelAndView revise(String name,String pass){
        ModelAndView mv = new ModelAndView();
        boolean updata = loginImpl.updata(name, pass);

        if (updata){

            mv.addObject("reviseSuccess","修改成功");

            mv.setViewName("index");

        }else {

            mv.addObject("reviseError","修改失败");

            mv.setViewName("revise");

        }

        return mv;


    }









}
