package com.spring.config.shiro;
import com.spring.shiro.realm.realm;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.*;

@Configuration
public class shiroConfig {


    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        securityManager.setAuthenticator(realmAuthenticator());
        return securityManager;
    }
    @Bean
    public SessionManager sessionManager(){
        SessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;

    }
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "cacheManagerLocalShiro")
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:configFile/ehcache-spring.xml");
        return ehCacheManager;
    }

    @Bean(name = "ShiroRealm")
    public realm shiroRealm(){
       return new realm();

    }
    @Bean(name = "shiroFile")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/spring/Login","anon");
//        linkedHashMap.put("/","anon");
        linkedHashMap.put("/**", "anon");
        linkedHashMap.put("/static/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/spring/mybatis");
        shiroFilterFactoryBean.setUnauthorizedUrl("/spring/errorPassword");
        return shiroFilterFactoryBean;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean(name = "realmAuthenticatorLocal")
    public ModularRealmAuthenticator realmAuthenticator(){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        List collection = new ArrayList();
        collection.add(shiroRealm());
        authenticator.setRealms(collection);
        return authenticator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor sourceAdvisor(){

        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;

    }

}
