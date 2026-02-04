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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}
