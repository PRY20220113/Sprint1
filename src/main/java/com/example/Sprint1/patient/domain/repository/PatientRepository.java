package com.example.Sprint1.patient.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Sprint1.patient.domain.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select d from Patient d where d.dni = ?1")
    Optional<Patient> FindByDni(Long dni);

    Boolean existsByDni(Long dni);
}
