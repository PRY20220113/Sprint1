package com.example.Sprint1.paciente.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class SavePacienteResource {
    @NotBlank
    @NotNull
    @Size(max = 150)
    private String nombre;

    @NotBlank
    @NotNull
    @Size(min = 0, max = 150)
    private int edad;

    @NotBlank
    @NotNull
    private String genero;

    @NotBlank
    @NotNull
    private String enfCronica; //Enfermedad Cronica

    @NotBlank
    @NotNull
    private String tSangre; //Tipo de Sangre
}
