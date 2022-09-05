package com.example.Sprint1.doctor.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDoctorResource {
    
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String first_name;    

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String last_name;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    private Long dni;
    
    @NotNull
    private String sfeesNum; //Numero de colegiatura 

    @NotNull
    private String phone;

}
