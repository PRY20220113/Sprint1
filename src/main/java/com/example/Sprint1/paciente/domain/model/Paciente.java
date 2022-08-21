package com.example.Sprint1.paciente.domain.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.Sprint1.medico.domain.model.Medico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 150)
    private String nombre;

    @NotNull
    private Integer edad;

    @NotBlank
    @NotNull
    private String genero;

    @NotBlank
    @NotNull
    private String enfCronica; //Enfermedad Cronica

    @NotBlank
    @NotNull
    private String tSangre; //Tipo de Sangre

    //@NotBlank
    //@NotNull
    //private List<String> alergias;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id", nullable = false)
    private Medico medico;
}
