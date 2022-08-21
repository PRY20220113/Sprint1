package com.example.Sprint1.medico.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.medico.domain.model.Medico;
import com.example.Sprint1.medico.domain.repository.MedicoRepository;
import com.example.Sprint1.medico.domain.service.MedicoService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;

@Service
public class MedicoServiceImpl implements MedicoService {

    private static final String ENTITY = "Medico";
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private Validator validator;

    @Override
    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico getByMedicoId(Long medicoId) {
        return medicoRepository.findById(medicoId)
        .orElseThrow( () -> new ResourceNotFoundException(ENTITY, medicoId));
    }

    @Override
    public Medico createMedico(Medico request) {
        Set<ConstraintViolation<Medico>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingEmail = medicoRepository.FindByEmail(request.getEmail());
        
        if(existingEmail != null) {
            throw new ResourceValidationException("email is already taken");
        }

        return medicoRepository.save(request);
    }

    @Override
    public Medico updateMedico(Long medicoId, Medico request) {
        Set<ConstraintViolation<Medico>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return medicoRepository.findById(medicoId).map( data ->
                medicoRepository.save(
                            data.withName(request.getName())
                                .withSurname(request.getSurname())
                                .withEmail(request.getEmail())
        )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicoId));
    }

    @Override
    public ResponseEntity<?> deleteMedico(Long medicoId) {
        return medicoRepository.findById(medicoId).map(data -> {
            medicoRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicoId));
    }
    
}
