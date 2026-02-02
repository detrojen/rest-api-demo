package com.example.rest_api_demo.Exceptions;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound(){
        super("employee not found");
    }
}
