package com.example.Sprint1.security.mapping;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.model.enumeration.RoleEnum;
import com.example.Sprint1.security.resource.RoleResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class RoleMapper {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<RoleEnum, String> roleToString = new AbstractConverter<>() {
        @Override
        protected String convert(RoleEnum role) {
            return role == null ? null : role.name();
        }
    };

    public RoleResource toResource(Role model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, RoleResource.class);
    }

    public Page<RoleResource> modelListToPage(List<Role> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, RoleResource.class), pageable, modelList.size());
    }

    public List<RoleResource> modelListToListResource(List<Role> modelList) {
        mapper.addConverter(roleToString);
        return mapper.mapList(modelList, RoleResource.class);
    }
}   
