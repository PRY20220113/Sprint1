package com.example.Sprint1.patient.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Sprint1.recipe.domain.entity.Recipe;
import com.example.Sprint1.security.domain.model.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    private String chronicD; //chronic disease

    @NotNull
    private String allergy;

    @NotNull
    @NotBlank
    private String password;

    //relationship
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "doctor_id", nullable = false)
    //private Doctor doctor;

    //relation with recipe
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    //relation with role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_roles",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
