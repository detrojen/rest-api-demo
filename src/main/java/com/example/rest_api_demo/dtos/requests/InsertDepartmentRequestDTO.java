package com.example.rest_api_demo.dtos.requests;

public class InsertDepartmentRequestDTO {
    private String departmentName;
    public String getDepartmentName(){
        return departmentName;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
}
