package com.example.Sprint1.medico.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sprint1.medico.domain.service.MedicoService;
import com.example.Sprint1.medico.mapping.MedicoMapper;
import com.example.Sprint1.medico.resource.MedicoResource;
import com.example.Sprint1.medico.resource.SaveMedicoResource;

@RestController
@RequestMapping("/api/v1/medico")
public class MedicoController {

    private final MedicoService medicoService;

    private final MedicoMapper mapper;

    public MedicoController(MedicoService medicoService, MedicoMapper mapper) {
        this.medicoService = medicoService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MedicoResource> GetAllMedicos() {
        return mapper.modelListToResource(medicoService.getAllMedicos());
    }

    @GetMapping("{medicoId}")
    public MedicoResource GetMedicoById(@PathVariable("medicoId") Long medicoId) {
        return mapper.toResource(medicoService.getByMedicoId(medicoId));
    }

    @PostMapping
    public MedicoResource CreateMedico(@RequestBody SaveMedicoResource request) {
        return mapper.toResource(medicoService.createMedico(mapper.toModel(request)));
    }

    @PutMapping("{medicoId}")
    public MedicoResource UpdateMedico(@PathVariable("medicoId") Long medicoId, @RequestBody SaveMedicoResource request) {
        return mapper.toResource(medicoService.updateMedico(medicoId, mapper.toModel(request)));
    }

    @DeleteMapping("{medicoId}")
    public ResponseEntity<?> DeleteMedico(@PathVariable("medicoId") Long medicoId) {
        return medicoService.deleteMedico(medicoId);
    }




}
