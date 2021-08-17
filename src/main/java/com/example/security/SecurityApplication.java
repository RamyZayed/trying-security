package com.example.security;

import  com.example.security.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run( new Class []{SecurityApplication.class, SecurityConfig.class}, args);
    }

}
