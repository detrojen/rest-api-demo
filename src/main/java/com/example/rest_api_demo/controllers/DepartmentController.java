package com.example.rest_api_demo.controllers;

import com.example.rest_api_demo.dtos.requests.InsertDepartmentRequestDTO;
import com.example.rest_api_demo.entities.Department;
import com.example.rest_api_demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @GetMapping("/departments/{departmentId}")
    public Department getDepartmentById(Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }
    @PostMapping("/departments")
    public Department insertDepartment(InsertDepartmentRequestDTO requestDTO){
        return departmentService.insertDepartment(requestDTO);
    }
}
