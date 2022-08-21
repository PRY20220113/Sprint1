package com.example.Sprint1.paciente.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.paciente.domain.model.Paciente;
import com.example.Sprint1.paciente.resource.PacienteResource;
import com.example.Sprint1.paciente.resource.SavePacienteResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class PacienteMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public PacienteResource toResource(Paciente model) {
        return mapper.map(model, PacienteResource.class);
    }

    public List<PacienteResource> modelListToResource(List<Paciente> modelList) {
        return mapper.mapList(modelList, PacienteResource.class);
    }

    public Paciente toModel(SavePacienteResource resource) {
        return mapper.map(resource, Paciente.class);
    }
}
