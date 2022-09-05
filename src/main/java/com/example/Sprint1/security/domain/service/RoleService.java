package com.example.Sprint1.security.domain.service;

import java.util.List;

import com.example.Sprint1.security.domain.model.entity.Role;

public interface RoleService {
    void seed();
    List<Role> getAll();
}
