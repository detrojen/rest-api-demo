package com.example.rest_api_demo.services;

import com.example.rest_api_demo.dtos.requests.InsertDepartmentRequestDTO;
import com.example.rest_api_demo.entities.Department;
import com.example.rest_api_demo.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public Department insertDepartment(InsertDepartmentRequestDTO requestDTO){
        Department department = modelMapper.map(requestDTO,Department.class);
        return departmentRepository.save(department);
    }
}
