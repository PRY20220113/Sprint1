package com.example.Sprint1.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("SecurityMapppingConfiguration")
public class MappingConfiguration {
    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }
}
