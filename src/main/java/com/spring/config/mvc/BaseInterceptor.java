package com.spring.config.mvc;
import com.spring.EntiryPage.mysqlEntiry.UserEntiry;
import com.spring.Service.impl.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private final Service service;

    @Autowired
    public BaseInterceptor(Service service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String name = request.getParameter("name");
        UserEntiry user = service.getUserByUserEntiry(name);
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(user.getName());
        arrayList.add(user.getPassword());
        arrayList.add(user.getId());
        boolean empty = arrayList.isEmpty();
        if (user!=null){
            System.out.println("empty = " + empty);
            return true;
        }
        try {
            response.sendRedirect("/spring/errorPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
