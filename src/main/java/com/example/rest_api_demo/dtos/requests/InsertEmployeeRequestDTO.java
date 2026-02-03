package com.example.rest_api_demo.dtos.requests;

import com.example.rest_api_demo.dtos.markers.OnCreate;
import com.example.rest_api_demo.dtos.markers.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.Default;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class InsertEmployeeRequestDTO {
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @Past
    private Date dob;

    @Positive
    private int baseSalary;

    @Email( groups = {OnCreate.class, OnUpdate.class, Default.class})
    private String email;

    @NotBlank(message = "password is required")
    private String password;


    private String profileImg;

    @Positive
    private Long departmentId;


    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getProfileImg(){
        return profileImg;
    }

    public void setProfileImg(String profileImg){
        this.profileImg = profileImg;
    }

    public Date getDob(){
        return dob;
    }

    public void setDob(Date dob){
        this.dob = dob;
    }

    public int getBaseSalary(){
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary){
        this.baseSalary = baseSalary;
    }

    public Long getDepartmentId(){
        return departmentId;
    }

    public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
    }

//    public MultipartFile getProfileImgFile(){
//        return profileImgFile;
//    }
//
//    public void setProfileImgFile(MultipartFile profileImgFile){
//        this.profileImgFile = profileImgFile;
//    }
}
