package com.example.Sprint1.doctor.domain.service.communication;

import com.example.Sprint1.doctor.resource.AuthenticateDoctorResource;
import com.example.Sprint1.shared.domain.service.communication.BaseResponse;

public class AuthenticateDoctorResponse extends BaseResponse<AuthenticateDoctorResource> {
    public AuthenticateDoctorResponse(String message) {
        super(message);
    }
    
    public AuthenticateDoctorResponse(AuthenticateDoctorResource resource) {
        super(resource);
    }
}
