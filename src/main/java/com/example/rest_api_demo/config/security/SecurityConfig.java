//package com.example.rest_api_demo.config.security;
//
//import com.example.rest_api_demo.filters.AuthTockenFilter;
//import com.example.rest_api_demo.filters.FilterChainExceptionHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class SecurityConfig {
//
//    @Autowired
//    AuthTockenFilter authTockenFilter;
//    @Autowired
//    FilterChainExceptionHandler filterChainExceptionHandler;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf().disable() // disable CSRF for simplicity, enable in production if needed
//                .authorizeHttpRequests()
//                .requestMatchers("/v1/auth/login").permitAll() // allow login endpoint
//                .requestMatchers("/swagger-ui/**").permitAll() // allow Swagger UI
//                .requestMatchers("/v3/api-docs/**").permitAll() // allow OpenAPI
//                .anyRequest().authenticated() // secure all other endpoints
//                .and()
//                .addFilterBefore(filterChainExceptionHandler, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(authTockenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//}