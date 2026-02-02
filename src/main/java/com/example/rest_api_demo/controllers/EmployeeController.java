package com.example.rest_api_demo.controllers;

import com.example.rest_api_demo.dtos.markers.OnCreate;
import com.example.rest_api_demo.dtos.markers.OnUpdate;
import com.example.rest_api_demo.dtos.requests.InsertEmployeeRequestDTO;
import com.example.rest_api_demo.dtos.requests.UpdateEmployeeDepartmentRequestDTO;
import com.example.rest_api_demo.entities.Employee;
import com.example.rest_api_demo.services.EmployeeService;
import com.example.rest_api_demo.utils.FileHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/v1/employees")
    public Employee InsertEmployee(  @RequestPart  InsertEmployeeRequestDTO employee, @RequestPart MultipartFile profileImgFile){
        System.out.println("uri versoning:- v1 called ");
        String filePath = FileHandler.Save(profileImgFile,"profileImgs");
        employee.setProfileImg(filePath);
        return employeeService.insertEmployee(employee);
    }

    @PostMapping(path = "/v2/employees")
    public Employee InsertEmployeeV2(@RequestPart InsertEmployeeRequestDTO employee, @RequestPart MultipartFile profileImgFile){
        System.out.println("uri versoning:- v2 called ");
        String filePath = FileHandler.Save(profileImgFile,"profileImgs");
        employee.setProfileImg(filePath);
        return employeeService.insertEmployee(employee);
    }

    @PutMapping(value = "/employees/{employeeId}" ,params = "version=1")
    public Employee updateEmployee(@PathVariable Long employeeId,   @Validated(OnCreate.class) @RequestBody InsertEmployeeRequestDTO employee){
        System.out.println("param versoning : v1 called ");
        return employeeService.updateEmployee(employeeId,employee);
    }

    @PutMapping(value = "/employees/{employeeId}" ,params = "version=2")
    public Employee updateEmployeeV2(@PathVariable Long employeeId, @RequestBody InsertEmployeeRequestDTO employee){
        System.out.println("param versoning : v2 called ");
        return employeeService.updateEmployee(employeeId,employee);
    }

    @PatchMapping(value = "/employees/{employeeId}/update-department", headers = "X-API-VERSION=1")
    public Employee updateDepartmentOnly(@PathVariable Long employeeId, @RequestBody UpdateEmployeeDepartmentRequestDTO reqDTO){
        System.out.println("HEADER versoning : v1 called ");
        return employeeService.updateEmployeeDepartment(employeeId,reqDTO);
    }

    @PatchMapping("/v1/employees/{employeeId}/profile-img")
    public Employee changeProfileImg(@PathVariable Long employeeId, @RequestPart MultipartFile profileImgFile){
        String imgPath = FileHandler.Save(profileImgFile,"profileImgs");
        Employee employee = employeeService.updateProfileImg(employeeId,imgPath);
        return employee;
    }

    @GetMapping("/v1/employees")
    public List<Employee>  getAll(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/v1/employees/{employeeId}")
    public Employee  getAll(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

}
