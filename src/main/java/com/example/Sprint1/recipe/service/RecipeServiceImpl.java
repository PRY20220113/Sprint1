package com.example.Sprint1.recipe.service;

import com.example.Sprint1.patient.domain.repository.PatientRepository;
import com.example.Sprint1.recipe.domain.entity.Recipe;
import com.example.Sprint1.recipe.domain.persistence.RecipeRepository;
import com.example.Sprint1.recipe.domain.service.RecipeService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final String ENTITY = "Recipe";

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validator validator;

    public List<Recipe> getAllRecipes() { return recipeRepository.findAll(); }

    @Override
    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }

    @Override
    public List<Recipe> getAllByPatientId(Long patientId) {
        var exitingPatient = patientRepository.findById(patientId);

        if(exitingPatient.isEmpty())
            throw new ResourceNotFoundException("Patient", patientId);

        return recipeRepository.findByPatientId(patientId);
    }

    @Override
    public Recipe createRecipe(Long patientId, Recipe request) {
        Set<ConstraintViolation<Recipe>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map(patient -> {
            request.setPatient(patient);
            return recipeRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Patient", patientId));
    }

    @Override
    public Recipe updateRecipe(Long recipeId, Recipe request) {
        Set<ConstraintViolation<Recipe>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return recipeRepository.findById(recipeId).map(recipe ->
                recipeRepository.save(
                        recipe.withProduct(request.getProduct())
                                .withWeight(request.getWeight())
                                .withCant(request.getCant())
                                .withEachHour(request.getEachHour())
                                .withCantTomas(request.getCantTomas()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }

    @Override
    public ResponseEntity<?> deleteRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId).map(recipe -> {
            recipeRepository.delete(recipe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }
}
