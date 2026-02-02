package com.example.rest_api_demo.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class InsertEmployeeRequestDTO {
    private String firstName;

    private String lastName;

    private Date dob;

    private int baseSalary;

    private String email;

    private String password;

    private String profileImg;

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
