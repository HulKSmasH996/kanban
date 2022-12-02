package com.mytasknowcobackend.kanban.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration  {


    protected void configure(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer()
                .jwt();
        http.

                 cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated();


    }
}