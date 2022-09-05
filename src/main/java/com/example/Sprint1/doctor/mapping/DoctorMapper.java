package com.example.Sprint1.doctor.mapping;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.doctor.resource.SaveDoctorResource;
import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class DoctorMapper {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Role, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(Role role) {
            return role == null ? null : role.getName().name();
        }
    };

    public DoctorResource toResource(Doctor model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, DoctorResource.class);
    }

    public List<DoctorResource> modelListToResource(List<Doctor> modelList) {
        mapper.addConverter(roleToString);
        return mapper.mapList(modelList, DoctorResource.class);
    }

    public Doctor toModel(SaveDoctorResource resource) {
        return mapper.map(resource, Doctor.class);
    }
}
