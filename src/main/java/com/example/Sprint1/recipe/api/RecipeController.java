package com.example.Sprint1.recipe.api;

import com.example.Sprint1.recipe.domain.service.RecipeService;
import com.example.Sprint1.recipe.mapping.RecipeMapper;
import com.example.Sprint1.recipe.resource.CreateRecipeResource;
import com.example.Sprint1.recipe.resource.RecipeResource;
import com.example.Sprint1.recipe.resource.UpdateRecipeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeMapper mapper;

    public RecipeController(RecipeService recipeService, RecipeMapper mapper) {
        this.recipeService = recipeService;
        this.mapper = mapper;
    }

    @GetMapping("recipies")
    public List<RecipeResource> getAllRecipes() {
        return mapper.modelListToResource(recipeService.getAllRecipes());
    }

    @GetMapping("recipe/{recipeId}")
    public RecipeResource getRecipeById(@PathVariable("recipeId") Long recipeId) {
        return mapper.toResource(recipeService.getRecipeById(recipeId));
    }

    @GetMapping("patient/{patientId}/recipe")
    public List<RecipeResource> getAllByPatientId(@PathVariable("patientId") Long patientId){
        return mapper.modelListToResource(recipeService.getAllByPatientId(patientId));
    }

    @PostMapping("patient/{patientId}/recipe")
    public RecipeResource createRecipe(@PathVariable("patientId")Long patientId, @RequestBody CreateRecipeResource request) {
        return mapper.toResource(recipeService.createRecipe(patientId, mapper.toModel(request)));
    }

    @PutMapping("recipe/{recipeId}")
    public RecipeResource updateRecipe(@PathVariable("recipeId") Long recipeId, @RequestBody UpdateRecipeResource request) {
        return mapper.toResource(recipeService.updateRecipe(recipeId, mapper.toModel(request)));
    }

    @DeleteMapping("recipe/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        return recipeService.deleteRecipe(recipeId);
    }
}
