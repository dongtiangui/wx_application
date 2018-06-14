package com.spring.boot;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.exceptions.TemplateInputException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class ExpretionAppcalition {
    @ExceptionHandler(value = {TemplateInputException.class})
    @RequestMapping
    public ModelAndView Result(HttpServletRequest request, Exception e){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> result = new HashMap<>();
        result.put("error","错误");
        mv.addObject("error",result);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
