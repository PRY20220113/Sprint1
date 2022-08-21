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
@RequestMapping("/api/v1/directors")
public class MedicoController {

    private final MedicoService directorService;

    private final MedicoMapper mapper;

    public MedicoController(MedicoService directorService, MedicoMapper mapper) {
        this.directorService = directorService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MedicoResource> GetAllMedicos() {
        return mapper.modelListToResource(directorService.getAllMedicos());
    }

    @GetMapping("{medicoId}")
    public MedicoResource GetMedicoById(@PathVariable("medicoId") Long medicoId) {
        return mapper.toResource(directorService.getByMedicoId(medicoId));
    }

    @PostMapping
    public MedicoResource CreateMedico(@RequestBody SaveMedicoResource request) {
        return mapper.toResource(directorService.createMedico(mapper.toModel(request)));
    }

    @PutMapping("{medicoId}")
    public MedicoResource UpdateMedico(@PathVariable("medicoId") Long medicoId, @RequestBody SaveMedicoResource request) {
        return mapper.toResource(directorService.updateMedico(medicoId, mapper.toModel(request)));
    }

    @DeleteMapping("{medicoId}")
    public ResponseEntity<?> DeleteMedico(@PathVariable("medicoId") Long medicoId) {
        return directorService.deleteMedico(medicoId);
    }




}
