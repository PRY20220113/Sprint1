package com.example.Sprint1.doctor.resource;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResource {
    private Long id;
    
    private String first_name; 

    private String last_name;

    private String email;

    private Long dni;

    private String sfeesNum; //Numero de colegiatura 

    private String phone;

    private List<String> roles;
}
