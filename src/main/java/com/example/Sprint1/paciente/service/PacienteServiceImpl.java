package com.example.Sprint1.paciente.service;

import java.util.List;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.medico.domain.repository.MedicoRepository;
import com.example.Sprint1.paciente.domain.model.Paciente;
import com.example.Sprint1.paciente.domain.respository.PacienteRepository;
import com.example.Sprint1.paciente.domain.service.PacienteService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final String ENTITY = "Paciente";
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private Validator validator;

    @Override
    public List<Paciente> getAllByMedicoId(Long medicoId) {
        var exitingMedico = medicoRepository.findById(medicoId);

        if(exitingMedico.isEmpty())
            throw new ResourceNotFoundException(ENTITY, medicoId);

        return pacienteRepository.findByMedicoId(medicoId);
    }

    @Override
    public Paciente createPaciente(Long medicoId, Paciente request) {
        Set<ConstraintViolation<Paciente>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return medicoRepository.findById(medicoId).map(medico -> {
            request.setMedico(medico);
            return pacienteRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicoId));
    }

    @Override
    public Paciente updatePaciente(Long pacienteId, Paciente request) {
        Set<ConstraintViolation<Paciente>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return pacienteRepository.findById(pacienteId).map(
                pacienteP -> pacienteRepository.save(
                       pacienteP.withNombre(request.getNombre())
                                .withEdad(request.getEdad())
                                .withGenero(request.getGenero())
                                .withEnfCronica(request.getEnfCronica())
                                .withTSangre(request.getTSangre()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, pacienteId));
    }

    @Override
    public ResponseEntity<?> deletePaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).map(pacienteP -> {
            pacienteRepository.delete(pacienteP);
            return ResponseEntity.ok().build();
        })
        .orElseThrow(() -> new ResourceNotFoundException(ENTITY, pacienteId));
    }
    
}
