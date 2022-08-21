package com.example.Sprint1.service;

import com.example.Sprint1.domain.entity.Recipe;
import com.example.Sprint1.domain.persistence.RecipeRepository;
import com.example.Sprint1.domain.service.RecipeService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private Validator validator;

    public List<Recipe> getAll() { return recipeRepository.findAll(); }

    @Override
    public Page<Recipe> getAll(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public Recipe getById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }

    @Override
    public Recipe create(Recipe request) {
        Set<ConstraintViolation<Recipe>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return recipeRepository.save(request);
    }

    @Override
    public Recipe update(Long recipeId, Recipe request) {
        Set<ConstraintViolation<Recipe>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return recipeRepository.findById(recipeId).map(recipe ->
                recipeRepository.save(
                        recipe.withProduct(request.getProduct())
                                .withWight(request.getWight())
                                .withCant(request.getCant())
                                .withEachHour(request.getEachHour()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }

    @Override
    public ResponseEntity<?> delete(Long recipeId) {
        return recipeRepository.findById(recipeId).map(recipe -> {
            recipeRepository.delete(recipe);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, recipeId));
    }
}
