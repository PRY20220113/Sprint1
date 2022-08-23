package com.example.Sprint1.domain.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String product;

    @NotNull
    @NotBlank
    @Size(max = 5000)
    private String wight;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String cant;

    @NotNull
    @NotBlank
    @Size(max = 24)
    private String eachHour;
}
