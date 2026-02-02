package com.example.rest_api_demo.dtos.requests;

public class UpdateEmployeeDepartmentRequestDTO {
    private Long departmentId;

    public Long getDepartmentId(){
        return departmentId;
    }

    public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
    }
}
