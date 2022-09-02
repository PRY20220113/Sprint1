package com.example.Sprint1.recipe.mapping;

import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.recipe.domain.entity.Recipe;
import com.example.Sprint1.recipe.resource.CreateRecipeResource;
import com.example.Sprint1.recipe.resource.RecipeResource;
import com.example.Sprint1.recipe.resource.UpdateRecipeResource;
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

    public List<RecipeResource> modelListToResource(List<Recipe> modelList) {
        return mapper.mapList(modelList, RecipeResource.class);
    }

    public Recipe toModel(CreateRecipeResource resource) {
        Recipe recipe = new Recipe();
        recipe.setProduct(resource.getProduct());
        recipe.setWight(resource.getWight());
        recipe.setCant(resource.getCant());
        recipe.setEachHour(resource.getEachHour());
        recipe.setCantTomas(resource.getCantTomas());
        return mapper.map(resource, Recipe.class);
    }

    public Recipe toModel(UpdateRecipeResource resource) { return mapper.map(resource, Recipe.class); }
}
