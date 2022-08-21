package com.example.Sprint1.medico.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.Sprint1.paciente.domain.model.Paciente;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @NotBlank
    @Size(max = 50)
    private String name;    

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String surname;
    
    @NonNull
    @NotBlank
    @Size(max = 50)
    private String email;

    //relation with paciente
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Paciente> pacientes;
}
