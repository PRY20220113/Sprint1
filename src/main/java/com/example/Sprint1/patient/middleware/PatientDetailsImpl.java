package com.example.Sprint1.patient.middleware;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Sprint1.patient.domain.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatientDetailsImpl implements UserDetails {
    
    private Long id;
    
    private String name;

    private Integer age;

    private Long dni;

    private String email;

    private String gener;

    private String bloodT; //Blood Type

    private String chronicD; //chronic disease

    private String allergy;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        PatientDetailsImpl user = (PatientDetailsImpl) other;
        return Objects.equals(id, user.id);
    }

    public static PatientDetailsImpl build(Patient user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new PatientDetailsImpl(user.getId(), user.getName(), user.getAge(), user.getDni(), user.getEmail(), user.getGener(), user.getBloodT(), user.getChronicD(), user.getAllergy(), user.getPassword(), authorities);
    }
}
