package com.example.Sprint1.recipe.domain.persistence;

import com.example.Sprint1.recipe.domain.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByPatientId(Long patientId);
}
