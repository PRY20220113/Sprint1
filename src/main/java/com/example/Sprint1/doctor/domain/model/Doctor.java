package com.example.Sprint1.doctor.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.shared.domain.model.AuditModel;

import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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

    @NotNull
    @NotBlank
    private String password;

    //relation with patient
    //@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<Patient> patients;

    //relation with role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_roles",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
