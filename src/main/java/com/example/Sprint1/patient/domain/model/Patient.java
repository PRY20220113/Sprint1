package com.example.Sprint1.patient.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Sprint1.doctor.domain.model.Doctor;

import com.example.Sprint1.recipe.domain.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
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

    //relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    //relation with recipe
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Recipe> recipes;
}
