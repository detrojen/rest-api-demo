package com.example.rest_api_demo.controllers;

import com.example.rest_api_demo.dtos.markers.OnCreate;
import com.example.rest_api_demo.dtos.markers.OnUpdate;
import com.example.rest_api_demo.dtos.requests.InsertEmployeeRequestDTO;
import com.example.rest_api_demo.dtos.requests.UpdateEmployeeDepartmentRequestDTO;
import com.example.rest_api_demo.entities.Employee;
import com.example.rest_api_demo.services.EmployeeService;
import com.example.rest_api_demo.utils.FileHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Operation(
            description = "create employee (routing level versoning v1)",
            responses = {
                    @ApiResponse(responseCode ="201",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    @PostMapping(path = "/v1/employees")
    public Employee InsertEmployee(  @RequestPart  InsertEmployeeRequestDTO employee, @RequestPart MultipartFile profileImgFile){
        System.out.println("uri versoning:- v1 called ");
        String filePath = FileHandler.Save(profileImgFile,"profileImgs");
        employee.setProfileImg(filePath);
        return employeeService.insertEmployee(employee);
    }

    @PostMapping(path = "/v2/employees")
    @Operation(
            description = "create employee (routing level versoning v2)",
            responses = {
                    @ApiResponse(responseCode ="201",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public Employee InsertEmployeeV2(@Validated(OnCreate.class)@RequestPart InsertEmployeeRequestDTO employee, @RequestPart MultipartFile profileImgFile){
        System.out.println("uri versoning:- v2 called ");
        String filePath = FileHandler.Save(profileImgFile,"profileImgs");
        employee.setProfileImg(filePath);
        return employeeService.insertEmployee(employee);
    }

    @PutMapping(value = "/employees/{employeeId}" ,params = "version=1")
    @Operation(
            description = "update employee (param level versoning v1)",
            responses = {
                    @ApiResponse(responseCode ="200",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public Employee updateEmployee(@PathVariable Long employeeId,   @Validated(OnUpdate.class) @RequestBody InsertEmployeeRequestDTO employee){
        System.out.println("param versoning : v1 called ");
        return employeeService.updateEmployee(employeeId,employee);
    }

    @PutMapping(value = "/employees/{employeeId}" ,params = "version=2")
    @Operation(
            description = "update employee (param level versoning v2)",
            responses = {
                    @ApiResponse(responseCode ="200",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public Employee updateEmployeeV2(@PathVariable Long employeeId, @Valid @RequestBody InsertEmployeeRequestDTO employee){
        System.out.println("param versoning : v2 called ");
        return employeeService.updateEmployee(employeeId,employee);
    }

    @PatchMapping(value = "/employees/{employeeId}/update-department", headers = "X-API-VERSION=1")
    @Operation(
            description = "update employee department (header level versoning v1)",
            responses = {
                    @ApiResponse(responseCode ="200",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public Employee updateDepartmentOnly(@PathVariable Long employeeId, @Validated(OnUpdate.class) @RequestBody UpdateEmployeeDepartmentRequestDTO reqDTO){
        System.out.println("HEADER versoning : v1 called ");
        return employeeService.updateEmployeeDepartment(employeeId,reqDTO);
    }

    @PatchMapping("/v1/employees/{employeeId}/profile-img")
    @Operation(
            description = "update employee department (header level versoning v2)",
            responses = {
                    @ApiResponse(responseCode ="200",description = "employee created"),
                    @ApiResponse(responseCode ="400",description = "validation failed"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public Employee changeProfileImg(@PathVariable Long employeeId, @RequestPart MultipartFile profileImgFile){
        String imgPath = FileHandler.Save(profileImgFile,"profileImgs");
        Employee employee = employeeService.updateProfileImg(employeeId,imgPath);
        return employee;
    }

    @GetMapping("/v1/employees")
    @Operation(
            description = "get all employees",
            responses = {
                    @ApiResponse(responseCode ="200",description = "employee list returned"),
                    @ApiResponse(responseCode ="500",description = "internal server error")
            }
    )
    public List<Employee>  getAll(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/v1/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable Long employeeId){
//        ResponseEntity.
        return ResponseEntity.ok()

                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES).cachePublic())
                .body(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping("/v1/employees/profile-img/{imgName}")
    public ResponseEntity<Resource> getProfileImg(@PathVariable String imgName, ServletWebRequest request) throws IOException {
        Resource file = FileHandler.Get("profileImgs",imgName);
        long lastModified = file.lastModified();
        if(request.checkNotModified(lastModified)){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .cacheControl(CacheControl.maxAge(5,TimeUnit.MINUTES).cachePublic())
                .lastModified(lastModified)
                .body(file);

    }

}
