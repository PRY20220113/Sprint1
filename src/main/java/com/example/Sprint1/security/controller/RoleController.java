package com.example.Sprint1.security.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import com.example.Sprint1.security.domain.service.RoleService;
import com.example.Sprint1.security.mapping.RoleMapper;
import com.example.Sprint1.security.resource.RoleResource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    private final RoleMapper mapper;

    public RoleController(RoleService roleService, RoleMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT')")
    public ResponseEntity<?> getAllRoles(Pageable pageable) {
        Page<RoleResource> resources = mapper.modelListToPage(roleService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }
}
