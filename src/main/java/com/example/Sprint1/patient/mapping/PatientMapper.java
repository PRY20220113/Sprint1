package com.example.Sprint1.patient.mapping;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.patient.resource.SavePatientResource;
import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class PatientMapper {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    public PatientResource toResource(Patient model) {
        mapper.addConverter(roleToString);
        PatientResource newModel = new PatientResource();
        newModel.setId(model.getId());
        newModel.setName(model.getName());
        newModel.setAge(model.getAge());
        newModel.setDni(model.getDni());
        newModel.setEmail(model.getEmail());
        newModel.setGener(model.getGener());
        newModel.setBloodT(model.getBloodT());
        newModel.setChronicD(model.getChronicD());
        newModel.setAllergy(model.getAllergy());
        return mapper.map(newModel, PatientResource.class);
    }

    public List<PatientResource> modelListToResource(List<Patient> modelList) {
        mapper.addConverter(roleToString);
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
