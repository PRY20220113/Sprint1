package com.example.Sprint1.paciente.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("PacienteApiConfiguration")
public class MappingConfiguration {
    @Bean
    public PacienteMapper pacienteMapper() {
        return new PacienteMapper();
    }
}
