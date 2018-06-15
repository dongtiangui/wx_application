package com.spring.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

@Component
@Configuration
public class WebMvcConfigurerMy implements WebMvcConfigurer {

    private BaseInterceptor baseInterceptor;
    private WebRequestInterceptorLocal webRequestInterceptorLocal;

    @Autowired
    public WebMvcConfigurerMy(BaseInterceptor baseInterceptor, WebRequestInterceptorLocal webRequestInterceptorLocal) {
        this.baseInterceptor = baseInterceptor;
        this.webRequestInterceptorLocal = webRequestInterceptorLocal;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(baseInterceptor).excludePathPatterns("/spring/mybatis");
        //registry.addInterceptor(baseInterceptor).addPathPatterns("/spring/Login");
        //registry.addWebRequestInterceptor(webRequestInterceptorLocal).addPathPatterns("/spring/Login");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        //registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/error/404").setViewName("/admin/page_error/error_404.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**");
    }
}
