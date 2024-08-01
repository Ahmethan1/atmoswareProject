package com.atmosware.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BaseApplicationConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Primary
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            // Kullanıcı detaylarını buradan döndürebilirsiniz
//            // Örneğin, veritabanından çekebilirsiniz
//            return User.withUsername(username)
//                    .password("password") // şifreyi encode ederek döndürmelisiniz
//                    .roles("USER") // roller
//                    .build();
//        };
//    }



}
