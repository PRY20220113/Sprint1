package com.example.Sprint1.security.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Sprint1.doctor.domain.service.DoctorService;
import com.example.Sprint1.doctor.middleware.JwtAuthorizationFilterDoctor;
import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.patient.middleware.JwtAuthorizationFilterPatient;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;


    @Autowired
    JwtAuthenticationEntryPointDoctor unauthorizedHandler;


    @Bean
    public JwtAuthorizationFilterDoctor authorizationFilterDoctor() {
        return new JwtAuthorizationFilterDoctor();
    }

    @Bean
    public JwtAuthorizationFilterPatient authorizationFilterPatient() {
        return new JwtAuthorizationFilterPatient();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(doctorService);
        builder.userDetailsService(patientService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/v1/doctors/auth/*", "/api/v1/patients/auth/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authorizationFilterDoctor(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authorizationFilterDoctor(), UsernamePasswordAuthenticationFilter.class);
    }
}
