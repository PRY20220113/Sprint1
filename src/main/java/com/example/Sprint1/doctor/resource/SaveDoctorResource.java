package com.example.Sprint1.doctor.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;

@Data
public class SaveDoctorResource {
    @NonNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String surname;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String email;
}
