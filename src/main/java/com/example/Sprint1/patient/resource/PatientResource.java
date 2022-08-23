package com.example.Sprint1.patient.resource;

import lombok.Data;

@Data
public class PatientResource {
    private Long id;
    private String name;
    private Integer age;
    private String gener;
    private String bloodT; //Blood Type
    //private List<String> enfCronica = new ArrayList<String>(); //chronic disease
    //private List<String> allergy;
}
