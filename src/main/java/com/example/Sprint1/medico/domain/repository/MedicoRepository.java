package com.example.Sprint1.medico.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Sprint1.medico.domain.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("select d from Medico d where d.email = ?1")
    Medico FindByEmail(String email);
}
