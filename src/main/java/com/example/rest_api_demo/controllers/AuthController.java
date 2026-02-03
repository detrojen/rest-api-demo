package com.example.rest_api_demo.controllers;

import com.example.rest_api_demo.dtos.requests.LoginRequestDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class AuthController {

    @PostMapping("/v1/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request, HttpServletResponse response){
        if(request.getEmail().equals("admin@gmail.com") && request.getPassword().equals("admin123")){
            ResponseCookie cookie = ResponseCookie.from("AUTH_TOKEN", "demo-tocken")
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("Strict")
                    .maxAge(Duration.ofMinutes(90))
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok().body("login successfull");
        }
        return ResponseEntity.badRequest().body("invalid credentials");
    }
}
