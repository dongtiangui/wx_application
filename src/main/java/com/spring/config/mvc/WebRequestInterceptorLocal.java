package com.spring.config.mvc;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
@Component
public class WebRequestInterceptorLocal implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        String contextPath = webRequest.getContextPath();
        System.out.println("contextPath = " + contextPath);
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
