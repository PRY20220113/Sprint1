package com.example.Sprint1.patient.resource;

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
public class AuthenticatePatientResource {
    private Long id;
    private String name;
    private Integer age;
    private Long dni;
    private String emial;
    private String gener;
    private String bloodT; //Blood Type
    private String chronicD; //chronic disease
    private String allergy;
    private List<String> roles;
    private String token;
}
