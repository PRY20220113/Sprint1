package com.example.Sprint1.recipe.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateRecipeResource {

    @NotNull
    @Size(max = 50)
    private String product;

    @NotNull
    private Integer weight;

    @NotNull
    private Integer cant;

    @NotNull
    private Integer eachHour;

    @NotNull
    private Integer cantTomas;
}
