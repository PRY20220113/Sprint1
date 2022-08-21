package com.example.Sprint1.medico.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("MedicoApiConfiguration")
public class MappingConfiguration {
    @Bean
    public MedicoMapper medicoMapper() {
        return new MedicoMapper();
    }
}
