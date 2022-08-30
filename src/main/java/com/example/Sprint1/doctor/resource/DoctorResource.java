package com.example.Sprint1.doctor.resource;

import lombok.Data;

@Data
public class DoctorResource {
    private int id;
    private String name;    
    private String surname;
    private String email;
    private Integer dni;
    private Integer sfeesNum; 
    private Integer phone;
    private String password;
}
