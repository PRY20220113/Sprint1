package com.example.Sprint1.patient.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;

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

import com.example.Sprint1.patient.domain.repository.PatientRepository;
import com.example.Sprint1.doctor.domain.repository.DoctorRepository;
import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.patient.domain.service.communication.AuthenticatePatientResponse;
import com.example.Sprint1.patient.domain.service.communication.RegisterPatientRequest;
import com.example.Sprint1.patient.domain.service.communication.RegisterPatientResponse;
import com.example.Sprint1.patient.middleware.JwtHandlerPatient;
import com.example.Sprint1.patient.middleware.PatientDetailsImpl;
import com.example.Sprint1.patient.resource.AuthenticatePatientResource;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.security.domain.model.entity.Role;
import com.example.Sprint1.security.domain.model.enumeration.RoleEnum;
import com.example.Sprint1.security.domain.repository.RoleRepository;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;
import com.example.Sprint1.shared.mapping.EnhancedModelMapper;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String ENTITY = "Patient";

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private Validator validator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandlerPatient handler;

    @Autowired
    EnhancedModelMapper mapper;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    //@Override
    //public List<Patient> getAllPatients() {
    //    return patientRepository.findAll();
    //}

    @Override
    public Patient getByPatientId(Long patientId) {
        return patientRepository.findById(patientId)
        .orElseThrow( () -> new ResourceNotFoundException(ENTITY, patientId));
    }

    //@Override
    //public Patient createPatient(Patient request) {
    //    Set<ConstraintViolation<Patient>> violations = validator.validate(request);
    //    if(!violations.isEmpty())
    //        throw new ResourceValidationException(ENTITY, violations);
    //
    //    var existingDni = patientRepository.FindByEmail(request.getEmail());
    //    
    //    if(existingDni != null) {
    //        throw new ResourceValidationException("DNI is already taken");
    //    }
    //
    //    return patientRepository.save(request);
    //}

    @Override
    public Patient updatePatient(Long patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map( data ->
                patientRepository.save(
                            data.withName(request.getName())
                                .withAge(request.getAge())
                                .withDni(request.getDni())
                                .withGener(request.getGener())
                                .withBloodT(request.getBloodT())
                                .withChronicD(request.getChronicD())
                                .withAllergy(request.getAllergy())
        )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public ResponseEntity<?> deletePatient(Long patientId) {
        return patientRepository.findById(patientId).map(data -> {
            patientRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public ResponseEntity<?> register(RegisterPatientRequest request) {
        if (patientRepository.existsByDni(request.getDni())) {
            AuthenticatePatientResponse response = new AuthenticatePatientResponse("Dni is already used.");
            return ResponseEntity.badRequest().body(response.getMessage());
        }

        if (doctorRepository.existsByDni(request.getDni())) {
            AuthenticatePatientResponse response = new AuthenticatePatientResponse("Dni is already used.");
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

            Patient user = new Patient();
            user.setName(request.getName());
            user.setAge(request.getAge());
            user.setDni(request.getDni());
            user.setEmail(request.getEmail());
            user.setGener(request.getGener());
            user.setBloodT(request.getBloodT());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setChronicD(request.getChronicD().toString().replace("[", "").replace("]", "").replaceAll("\\s+",""));
            user.setAllergy(request.getAllergy().toString().replace("[", "").replace("]", "").replaceAll("\\s+",""));
            user.setRoles(roles);
            
            patientRepository.save(user);
          
            PatientResource resource = mapper.map(user, PatientResource.class);
           
            RegisterPatientResponse response = new RegisterPatientResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            RegisterPatientResponse response = new RegisterPatientResponse(e.getMessage());
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

            PatientDetailsImpl userDetails = (PatientDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticatePatientResource resource = mapper.map(userDetails, AuthenticatePatientResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticatePatientResponse response = new AuthenticatePatientResponse(resource);

            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {
            AuthenticatePatientResponse response = new AuthenticatePatientResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient user = patientRepository.FindByDni(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return PatientDetailsImpl.build(user);
    }
}
