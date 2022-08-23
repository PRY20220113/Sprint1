package com.example.Sprint1.patient.resource;

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
    private String bloodT; //Blood Type

    //@ElementCollection
    //@CollectionTable(name="listOfUsers")
    //private List<String> enfCronica = new ArrayList<String>(); //chronic disease

    //@Column
    //@ElementCollection(targetClass=String.class)
    //private List<String> allergy;
}
