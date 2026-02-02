package com.example.rest_api_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Employee> employees;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id= id;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees(){
        return employees;
    }

    public void setEmployees(Set<Employee> employees){
        this.employees = employees;
    }
}
