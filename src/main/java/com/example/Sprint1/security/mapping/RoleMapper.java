package com.example.Sprint1.security.mapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.resource.RoleResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

public class RoleMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public RoleResource toResource(Role model) {
        return mapper.map(model, RoleResource.class);
    }
    
    public List<RoleResource> modelListToResource(List<Role> modelList) {
        return mapper.mapList(modelList, RoleResource.class);
    }
}   
