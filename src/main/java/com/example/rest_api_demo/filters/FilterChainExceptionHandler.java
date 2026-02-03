package com.example.rest_api_demo.filters;

import com.example.rest_api_demo.Exceptions.GlobalExceptionHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    @Autowired
    private GlobalExceptionHandler resolver;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
        try{
            filterChain.doFilter(request,response);
        }catch (Exception e){
            resolver.handleExcpetionResolver(e);
        }

    }
}
