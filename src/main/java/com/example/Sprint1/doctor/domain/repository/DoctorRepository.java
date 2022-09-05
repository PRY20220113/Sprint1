package com.example.Sprint1.doctor.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Sprint1.doctor.domain.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("select d from Doctor d where d.dni = ?1")
    Optional<Doctor> FindByDni(Long dni);

    Boolean existsByDni(Long dni);
}
