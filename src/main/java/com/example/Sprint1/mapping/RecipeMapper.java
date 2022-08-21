package com.example.Sprint1.mapping;

import com.example.Sprint1.domain.entity.Recipe;
import com.example.Sprint1.resource.CreateRecipeResource;
import com.example.Sprint1.resource.RecipeResource;
import com.example.Sprint1.resource.UpdateRecipeResource;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RecipeMapper implements Serializable{

    @Autowired
    EnhancedModelMapper mapper;

    public RecipeResource toResource(Recipe model) { return mapper.map(model, RecipeResource.class); }

    public Page<RecipeResource> modelListToPage(List<Recipe> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, RecipeResource.class), pageable, modelList.size());
    }

    public Recipe toModel(CreateRecipeResource resource) {
        return mapper.map(resource, Recipe.class);
    }

    public Recipe toModel(UpdateRecipeResource resource) { return mapper.map(resource, Recipe.class); }
}
