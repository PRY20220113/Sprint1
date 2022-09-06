package com.example.Sprint1.security.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.model.enumeration.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);

    boolean existsByName(RoleEnum name);
}
