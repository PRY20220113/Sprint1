package com.example.Sprint1.paciente.controller;

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

import com.example.Sprint1.paciente.domain.service.PacienteService;
import com.example.Sprint1.paciente.mapping.PacienteMapper;
import com.example.Sprint1.paciente.resource.PacienteResource;
import com.example.Sprint1.paciente.resource.SavePacienteResource;

@RestController
@RequestMapping("/api/v1")
public class PacienteController {
    private final PacienteService pacienteService;

    private final PacienteMapper mapper;

    public PacienteController(PacienteService pacienteService, PacienteMapper mapper) {
        this.pacienteService = pacienteService;
        this.mapper = mapper;
    }

    @GetMapping("medico/{medicoId}/paciente")
    public List<PacienteResource> GetTeachersByDirectorId(@PathVariable("medicoId") Long medicoId){
        return mapper.modelListToResource(pacienteService.getAllByMedicoId(medicoId));
    }

    @PostMapping("medico/{medicoId}/paciente")
    public PacienteResource CreateTeacher(@PathVariable("medicoId")Long medicoId, @RequestBody SavePacienteResource resource){
        return mapper.toResource(pacienteService.createPaciente(medicoId, mapper.toModel(resource)));
    }

    @PutMapping("paciente/{pacienteId}")
    public PacienteResource UpdateTeacher(@PathVariable("pacienteId") Long pacienteId, @RequestBody SavePacienteResource resource){
        return mapper.toResource(pacienteService.updatePaciente(pacienteId, mapper.toModel(resource)));
    }

    @DeleteMapping("paciente/{pacienteId}")
    public ResponseEntity<?> DeleteTeacher(@PathVariable("pacienteId") Long pacienteId){
        return pacienteService.deletePaciente(pacienteId);
    }
}
