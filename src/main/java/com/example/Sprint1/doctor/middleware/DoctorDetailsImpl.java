package com.example.Sprint1.doctor.middleware;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoctorDetailsImpl implements UserDetails {

    private Long id;
    
    private String first_name;     

    private String last_name;

    private String email;

    private Long dni;
    
    private String sfeesNum; //Numero de colegiatura 

    private String phone;

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
        DoctorDetailsImpl user = (DoctorDetailsImpl) other;
        return Objects.equals(id, user.id);
    }

    public static DoctorDetailsImpl build(Doctor user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new DoctorDetailsImpl(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getDni(), user.getSfeesNum(), user.getPhone(), user.getPassword(), authorities);
    }
    
    
}
