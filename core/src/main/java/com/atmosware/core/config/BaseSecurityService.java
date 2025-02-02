package com.atmosware.core.config;

import com.atmosware.core.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BaseSecurityService {
    private final JwtAuthFilter jwtAuthFilter;
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v1/api-docs",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/managementservice/api/v1/auth/login",
            "/questionservice/api/v1/questions/getAll",
            "/managementservice/api/v1/users/register",
            "/managementservice/api/v1/roles/add",
            "/managementservice/api/v1/userrole/add",
            "/managementservice/api/v1/roles/getById/{id}",
            "/examservice/api/v1/exam",
            "/questionservice/api/v1/exam-questions",
            "/examservice/api/v1/exam/add"

    };

    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req.requestMatchers(WHITE_LIST_URLS).permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity;
    }
}