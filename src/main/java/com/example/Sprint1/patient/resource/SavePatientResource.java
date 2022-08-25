package com.example.Sprint1.patient.resource;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class SavePatientResource {

    @NotBlank
    @NotNull
    @Size(max = 150)
    private String name;

    @NotNull
    private Integer age;

    @NotBlank
    @NotNull
    private String gener;

    @NotBlank
    @NotNull
    private String bloodT; 

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> chronicD; 

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> allergy;
}
