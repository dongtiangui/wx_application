package com.spring.config.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityMy extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/").permitAll();

//        http.authorizeRequests().antMatchers("/resources/**").permitAll()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/static/**").access("hasAnyRole()").anyRequest().authenticated().and();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager();
        memoryUserDetailsManager.createUser(User.withUsername("root").password("123456").authorities("admin").build());
        return memoryUserDetailsManager;
    }
    
}
