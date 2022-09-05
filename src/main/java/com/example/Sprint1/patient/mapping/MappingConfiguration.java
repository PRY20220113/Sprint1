package com.example.Sprint1.patient.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("PatientMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PatientMapper patientMapper() {
        return new PatientMapper();
    }
}
