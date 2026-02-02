package com.example.rest_api_demo.services;

import com.example.rest_api_demo.Exceptions.EmployeeNotFound;
import com.example.rest_api_demo.dtos.requests.InsertEmployeeRequestDTO;
import com.example.rest_api_demo.dtos.requests.UpdateEmployeeDepartmentRequestDTO;
import com.example.rest_api_demo.entities.Department;
import com.example.rest_api_demo.entities.Employee;
import com.example.rest_api_demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            throw new EmployeeNotFound();
        }
        return employee;
    }

    public Employee updateEmployeeDepartment(Long employeeId, UpdateEmployeeDepartmentRequestDTO reqDTO){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Department department = departmentService.getDepartmentById(reqDTO.getDepartmentId());
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);
        return  employee;
    }

    public Employee updateProfileImg(Long employeeId, String profileImg){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee == null){
            throw new EmployeeNotFound();
        }
        employee.setProfileImg(profileImg);
        employee = employeeRepository.save(employee);
        return employee;
    }

    public Employee insertEmployee(InsertEmployeeRequestDTO requestDTO){
        Employee employee = modelMapper.map(requestDTO,Employee.class);
        employee.setId(null);
        Department department = departmentService.getDepartmentById(requestDTO.getDepartmentId());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId,InsertEmployeeRequestDTO requestDTO){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Department department = departmentService.getDepartmentById(requestDTO.getDepartmentId());
        employee.setBaseSalary(requestDTO.getBaseSalary());
        employee.setDepartment(department);
        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setDob(requestDTO.getDob());
        employee.setEmail(requestDTO.getEmail());
        employee.setProfileImg(requestDTO.getProfileImg());
        return employeeRepository.save(employee);
    }


}