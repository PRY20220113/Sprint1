package com.example.Sprint1.doctor.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.doctor.domain.repository.DoctorRepository;
import com.example.Sprint1.doctor.domain.service.DoctorService;
import com.example.Sprint1.doctor.domain.service.communication.AuthenticateDoctorResponse;
import com.example.Sprint1.doctor.domain.service.communication.RegisterDoctorRequest;
import com.example.Sprint1.doctor.domain.service.communication.RegisterDoctorResponse;
import com.example.Sprint1.doctor.middleware.DoctorDetailsImpl;
import com.example.Sprint1.doctor.middleware.JwtHandlerDoctor;
import com.example.Sprint1.doctor.resource.AuthenticateDoctorResource;
import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.patient.domain.repository.PatientRepository;
import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.model.enumeration.RoleEnum;
import com.example.Sprint1.security.domain.repository.RoleRepository;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final String ENTITY = "Doctor";

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validator validator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandlerDoctor handler;

    @Autowired
    EnhancedModelMapper mapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    //@Override
    //public List<Doctor> getAllDoctors() {
    //    return doctorRepository.findAll();
    //}

    @Override
    public Doctor getByDoctorId(Long doctorId) {
        return doctorRepository.findById(doctorId)
        .orElseThrow( () -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    //@Override
    //public Doctor createDoctor(Doctor request) {
    //    Set<ConstraintViolation<Doctor>> violations = validator.validate(request);
    //    if(!violations.isEmpty())
    //        throw new ResourceValidationException(ENTITY, violations);
    //
    //    var existingDni = doctorRepository.FindByEmail(request.getEmail());
    //    
    //    if(existingDni != null) {
    //        throw new ResourceValidationException("DNI is already taken");
    //    }
    //
    //    return doctorRepository.save(request);
    //}

    @Override
    public Doctor updateDoctor(Long doctorId, Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return doctorRepository.findById(doctorId).map( data ->
                doctorRepository.save(
                            data.withFirst_name(request.getFirst_name())
                                .withLast_name(request.getLast_name())
                                .withEmail(request.getEmail())
                                .withDni(request.getDni())
                                .withSfeesNum(request.getSfeesNum())
                                .withPhone(request.getPhone())
        )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public ResponseEntity<?> deleteDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId).map(data -> {
            doctorRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public ResponseEntity<?> register(RegisterDoctorRequest request) {
        if (doctorRepository.existsByDni(request.getDni())) {
            AuthenticateDoctorResponse response = new AuthenticateDoctorResponse("Dni is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        if (patientRepository.existsByDni(request.getDni())) {
            AuthenticateDoctorResponse response = new AuthenticateDoctorResponse("Dni is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        try {
            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(RoleEnum.ROLE_DOCTOR)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(RoleEnum.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            Doctor user = new Doctor();
            user.setFirst_name(request.getFirst_name());
            user.setLast_name(request.getLast_name());
            user.setEmail(request.getEmail());
            user.setDni(request.getDni());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setSfeesNum(request.getSfeesNum());
            user.setPhone(request.getPhone());
            user.setRoles(roles);
            
            doctorRepository.save(user);
          
            DoctorResource resource = mapper.map(user, DoctorResource.class);
           
            RegisterDoctorResponse response = new RegisterDoctorResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            RegisterDoctorResponse response = new RegisterDoctorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getDni(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            DoctorDetailsImpl userDetails = (DoctorDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateDoctorResource resource = mapper.map(userDetails, AuthenticateDoctorResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateDoctorResponse response = new AuthenticateDoctorResponse(resource);

            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            AuthenticateDoctorResponse response = new AuthenticateDoctorResponse(String.format("An error occurred while authenticating: %s", e.getMessage())  + " Hola diego");
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctor user = doctorRepository.FindByDni(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return DoctorDetailsImpl.build(user);
    }
    
}
