package com.example.Sprint1.security.domain.service.communication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequest {
    @NotNull
    private Long dni;
    
    @NotBlank
    @NotNull
    private String password;
}
