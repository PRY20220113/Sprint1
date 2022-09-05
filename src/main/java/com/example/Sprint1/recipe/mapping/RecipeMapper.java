package com.example.Sprint1.recipe.mapping;

import com.example.Sprint1.recipe.domain.entity.Recipe;
import com.example.Sprint1.recipe.resource.CreateRecipeResource;
import com.example.Sprint1.recipe.resource.RecipeResource;
import com.example.Sprint1.recipe.resource.UpdateRecipeResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class RecipeMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public RecipeResource toResource(Recipe model) { return mapper.map(model, RecipeResource.class); }

    public List<RecipeResource> modelListToResource(List<Recipe> modelList) {
        return mapper.mapList(modelList, RecipeResource.class);
    }

    public Recipe toModel(CreateRecipeResource resource) {
        return mapper.map(resource, Recipe.class);
    }

    public Recipe toModel(UpdateRecipeResource resource) { return mapper.map(resource, Recipe.class); }
}
