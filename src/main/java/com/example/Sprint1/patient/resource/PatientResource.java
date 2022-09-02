package com.example.Sprint1.patient.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResource {
    private Long id;
    private String name;
    private Integer age;
    private String gener;
    private String bloodT; 
    private String chronicD;
    private String allergy;
}