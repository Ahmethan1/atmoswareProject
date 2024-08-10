package com.turkcell.gyt.examService.core.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return User.withUsername(username)
                    .password("password")
                    .roles("USER")
                    .build();
        };
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
