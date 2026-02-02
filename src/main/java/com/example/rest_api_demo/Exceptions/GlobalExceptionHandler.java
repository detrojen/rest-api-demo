package com.example.rest_api_demo.Exceptions;

import com.example.rest_api_demo.dtos.responses.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleEmployeeNotFound(EmployeeNotFound ex){
        return ResponseEntity.status(404).body(new ErrorResponseDTO(404,ex.getMessage(),new HashMap<String,Object>()));
    }
}
