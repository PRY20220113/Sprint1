package com.example.Sprint1.paciente.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.paciente.domain.model.Paciente;

@Service
public interface PacienteService {
    List<Paciente> getAllByMedicoId(Long medicoId);

    Paciente createPaciente(Long medicoId, Paciente paciente);

    Paciente updatePaciente(Long pacienteId, Paciente paciente);

    ResponseEntity<?> deletePaciente(Long pacienteId);
}
