package com.example.Sprint1.resource;

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
    @Size(max = 5000)
    private String wight;

    @NotNull
    @Size(max = 100)
    private String cant;

    @NotNull
    @Size(max = 24)
    private String eachHour;
}
