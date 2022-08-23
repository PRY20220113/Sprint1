package com.example.Sprint1.patient.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.patient.resource.SavePatientResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class PatientMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public PatientResource toResource(Patient model) {
        return mapper.map(model, PatientResource.class);
    }

    public List<PatientResource> modelListToResource(List<Patient> modelList) {
        return mapper.mapList(modelList, PatientResource.class);
    }

    public Patient toModel(SavePatientResource resource) {
        return mapper.map(resource, Patient.class);
    }
}
