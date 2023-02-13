package com.shaon.LDAPdemo.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager configureManager(HttpSecurity httpSecurity)throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups")
                .contextSource().url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare().passwordEncoder(new BCryptPasswordEncoder()).passwordAttribute("userPassword");
        return authenticationManagerBuilder.build();
    }



}
