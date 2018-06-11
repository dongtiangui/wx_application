package com.spring.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartResolverLocal {


    @Bean
    public MultipartResolver multipartResolver(){

        MultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;

    }

}
