package com.example.Sprint1.doctor.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("DoctorMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public DoctorMapper doctorMapper() {
        return new DoctorMapper();
    }
}
