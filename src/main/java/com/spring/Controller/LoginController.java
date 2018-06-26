package com.spring.Controller;
import com.spring.Service.impl.LoginImpl;
import com.spring.Service.impl.Service;
import com.spring.redis.LoginRedis;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Controller
public class LoginController {
    private final LoginRedis loginRedis;
    private final Service service;
    private final LoginImpl loginImpl;
    @Autowired
    public LoginController(Service service, LoginImpl loginImpl, LoginRedis loginRedis) {
        this.service = service;
        this.loginImpl = loginImpl;
        this.loginRedis = loginRedis;
    }
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String formController(String name, String pass, ModelMap map){
        UsernamePasswordToken Token = new UsernamePasswordToken(name,pass);
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if (!authenticated){
            try {
                subject.login(Token);
                if (loginImpl.getUserName(name).equals("student")){
                    map.addAttribute("name",name);
                    return "forward:./student";
                }
                if (loginImpl.getUserName(name).equals("admin")) {
                    map.addAttribute("name",name);
                    return "forward:./admin";
                }
            }catch (Exception e) {
                map.addAttribute("message","认证失败");
                return "index";
            }
        }
        return "index";
    }
//    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
//    public ModelAndView regise(@RequestParam("name") String name,
//                               @RequestParam("pass") String pass,
//                               @RequestParam("roles") String roles){
//        ModelAndView mv = new ModelAndView();
//        boolean insert = loginImpl.insert(name, pass,roles);
//        if (insert){
//            mv.setViewName("index");
//            mv.addObject("success","注册成功");
//        }else {
//            return new ModelAndView(new MappingJackson2JsonView());
//        }
//        return mv;
//    }
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
