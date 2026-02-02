package com.example.rest_api_demo.dtos.responses;

import java.util.HashMap;


public class ErrorResponseDTO {
    private int status;
    private String message;
    private HashMap<String, Object> errors;

    public ErrorResponseDTO(int status,String message,HashMap<String, Object> errors){
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
