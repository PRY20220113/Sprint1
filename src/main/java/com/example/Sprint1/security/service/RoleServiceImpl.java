package com.example.Sprint1.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.repository.RoleRepository;
import com.example.Sprint1.security.domain.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    
}
