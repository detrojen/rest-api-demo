//package com.example.rest_api_demo.filters;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.WebUtils;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class AuthTockenFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        if(request.getPathInfo().contains("swagger-ui")){
////            filterChain.doFilter(request, response);
////
////        }else{
////            Cookie tockenCookie = WebUtils.getCookie(request,"AUTH_TOCKEN");
////            if(tockenCookie!=null && tockenCookie.getValue().equals("demo-tocken")){
////                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
////                        "dummyUser",
////                        null,
////                        List.of(() -> "ROLE1")
////                );
////                SecurityContextHolder.getContext().setAuthentication(token);
////                filterChain.doFilter(request, response);
////            }else{
////                throw new RuntimeException("unathorised");
////            }
////        }
//        filterChain.doFilter(request, response);
//
//    }
//}