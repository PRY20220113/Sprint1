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
        PatientResource newModel = new PatientResource(model.getId(),model.getName(), model.getAge(), model.getGener(), model.getBloodT(), model.getChronicD(), model.getAllergy());
        return mapper.map(newModel, PatientResource.class);
    }

    public List<PatientResource> modelListToResource(List<Patient> modelList) {
        return mapper.mapList(modelList, PatientResource.class);
    }

    public Patient toModel(SavePatientResource resource) {
        Patient patient = new Patient();
        patient.setName(resource.getName());
        patient.setGener(resource.getGener());
        patient.setAge(resource.getAge());
        patient.setBloodT(resource.getBloodT());
        patient.setChronicD(resource.getChronicD().toString().replace("[", "").replace("]", "").replaceAll("\\s+",""));
        patient.setAllergy(resource.getAllergy().toString().replace("[", "").replace("]", "").replaceAll("\\s+",""));
        return mapper.map(patient, Patient.class);
    }
}
