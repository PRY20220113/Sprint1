package com.example.Sprint1.patient.domain.service.communication;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterPatientRequest {
    @NotBlank
    @NotNull
    @Size(max = 150)
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Long dni;

    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String gener;

    @NotBlank
    @NotNull
    private String bloodT; //Blood Type

    @NotNull
    @NotBlank
    private String password;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> chronicD;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> allergy;

    private Set<String> roles;

}
