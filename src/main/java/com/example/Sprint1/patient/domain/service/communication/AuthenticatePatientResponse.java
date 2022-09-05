package com.example.Sprint1.patient.domain.service.communication;

import com.example.Sprint1.patient.resource.AuthenticatePatientResource;
import com.example.Sprint1.shared.domain.service.communication.BaseResponse;

public class AuthenticatePatientResponse extends BaseResponse<AuthenticatePatientResource> {
    public AuthenticatePatientResponse(String message) {
        super(message);
    }
    
    public AuthenticatePatientResponse(AuthenticatePatientResource resource) {
        super(resource);
    }
}
