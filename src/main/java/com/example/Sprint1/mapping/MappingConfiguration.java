package com.example.Sprint1.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("recipeMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public RecipeMapper recipeMapper() { return new RecipeMapper(); }
}
