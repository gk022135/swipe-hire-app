
package com.job.demo.config;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration

public class AppConfig {



    @Bean // This makes the PasswordEncoder available to other parts of your app

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}

