package com.example.Sprint1.recipe.domain.entity;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.patient.domain.model.Patient;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String product;

    @NotNull
    private Integer wight;

    @NotNull
    private Integer cant;

    @NotNull
    private Integer eachHour;

    @NotNull
    private Integer cantTomas;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

}
