package com.example.Sprint1.security.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.model.enumeration.RoleEnum;
import com.example.Sprint1.security.domain.repository.RoleRepository;
import com.example.Sprint1.security.domain.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    private static String[] DEFAULT_ROLES = {"ROLE_DOCTOR", "ROLE_PATIENT"};

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            RoleEnum roleName = RoleEnum.valueOf(name);
            if(!roleRepository.existsByName(roleName)) {
                roleRepository.save((new Role()).withName(roleName));
            }
        });
    }
    
}
