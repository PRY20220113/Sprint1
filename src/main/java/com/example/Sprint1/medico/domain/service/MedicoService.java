package com.example.Sprint1.medico.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.medico.domain.model.Medico;

@Service
public interface MedicoService {
    List<Medico> getAllMedicos();
    Medico getByMedicoId(Long medicoId);
    Medico createMedico(Medico medico);
    Medico updateMedico(Long medicoId, Medico medico);
    ResponseEntity<?> deleteMedico(Long medicoId);
}
