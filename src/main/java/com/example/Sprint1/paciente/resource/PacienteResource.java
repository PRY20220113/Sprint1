package com.example.Sprint1.paciente.resource;

import lombok.Data;

@Data
public class PacienteResource {
    private Long id;
    private String nombre;
    private int edad;
    private String genero;
    private String enfCronica; //Enfermedad Cronica
    private String tSangre; //Tipo de Sangre
}
