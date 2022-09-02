package com.example.Sprint1.recipe.api;

import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.patient.mapping.PatientMapper;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.recipe.domain.service.RecipeService;
import com.example.Sprint1.recipe.mapping.RecipeMapper;
import com.example.Sprint1.recipe.resource.CreateRecipeResource;
import com.example.Sprint1.recipe.resource.RecipeResource;
import com.example.Sprint1.recipe.resource.UpdateRecipeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public List<RecipeResource> getAllRecipes() {
        return mapper.modelListToResource(recipeService.getAllRecipes());
    }

    @GetMapping("{recipeId}")
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
