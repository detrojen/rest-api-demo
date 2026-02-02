package com.example.rest_api_demo.dtos.responses;

import java.util.HashMap;
import java.util.Map;


public class ErrorResponseDTO {
    private int status;
    private String message;
    private Map<String, Object> errors;

    public ErrorResponseDTO(int status,String message,Map<String, Object> errors ){
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
