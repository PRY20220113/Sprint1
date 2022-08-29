package com.example.Sprint1.doctor.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.doctor.resource.SaveDoctorResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class DoctorMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public DoctorResource toResource(Doctor model) {
        return mapper.map(model, DoctorResource.class);
    }

    public List<DoctorResource> modelListToResource(List<Doctor> modelList) {
        return mapper.mapList(modelList, DoctorResource.class);
    }

    public Doctor toModel(SaveDoctorResource resource) {
        return mapper.map(resource, Doctor.class);
    }
}
