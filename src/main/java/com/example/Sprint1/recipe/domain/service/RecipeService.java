package com.example.Sprint1.recipe.domain.service;

import com.example.Sprint1.recipe.domain.entity.Recipe;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    Recipe getRecipeById(Long recipeId);

    List<Recipe> getAllByPatientId(Long patientId);

    Recipe createRecipe(Long patientId,Recipe recipe);

    Recipe updateRecipe(Long recipeId, Recipe request);

    ResponseEntity<?> deleteRecipe(Long recipeId);
}
