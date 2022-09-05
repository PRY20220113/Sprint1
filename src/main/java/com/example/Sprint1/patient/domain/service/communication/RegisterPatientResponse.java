package com.example.Sprint1.patient.domain.service.communication;

import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.shared.domain.service.communication.BaseResponse;


public class RegisterPatientResponse extends BaseResponse<PatientResource> {
    public RegisterPatientResponse(String message) {
        super(message);
    }

    public RegisterPatientResponse(PatientResource resource) {
        super(resource);
    }
}
