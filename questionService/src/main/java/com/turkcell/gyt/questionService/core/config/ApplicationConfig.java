package com.turkcell.gyt.questionService.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return username -> {
            // Kullanıcı detaylarını buradan döndürebilirsiniz
            // Örneğin, veritabanından çekebilirsiniz
            return User.withUsername(username)
                    .password("password") // şifreyi encode ederek döndürmelisiniz
                    .roles("USER") // roller
                    .build();
        };
    }
}
