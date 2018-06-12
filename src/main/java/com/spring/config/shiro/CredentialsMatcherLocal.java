package com.spring.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CredentialsMatcherLocal {

    @Bean(name = "credentialsMatcherLocal")
    public HashedCredentialsMatcher credentialsMatcher(){

         HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

         credentialsMatcher.setHashIterations(1024);
         credentialsMatcher.setHashAlgorithmName("MD5");

         return credentialsMatcher;

    }
}
