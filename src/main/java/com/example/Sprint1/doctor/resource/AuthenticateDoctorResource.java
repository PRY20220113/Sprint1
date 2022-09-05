package com.example.Sprint1.doctor.resource;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateDoctorResource {
    private Long id;
    
    private String first_name;    

    private String last_name;

    private String email;

    private Long dni;
    
    private String sfeesNum; //Numero de colegiatura 

    private String phone;

    private List<String> roles;
    
    private String token;
}
