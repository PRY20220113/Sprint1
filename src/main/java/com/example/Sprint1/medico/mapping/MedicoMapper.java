package com.example.Sprint1.medico.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.medico.domain.model.Medico;
import com.example.Sprint1.medico.resource.MedicoResource;
import com.example.Sprint1.medico.resource.SaveMedicoResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class MedicoMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public MedicoResource toResource(Medico model) {
        return mapper.map(model, MedicoResource.class);
    }

    public List<MedicoResource> modelListToResource(List<Medico> modelList) {
        return mapper.mapList(modelList, MedicoResource.class);
    }

    public Medico toModel(SaveMedicoResource resource) {
        return mapper.map(resource, Medico.class);
    }
}
