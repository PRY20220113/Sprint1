package com.example.Sprint1.doctor.domain.service.communication;

import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.shared.domain.service.communication.BaseResponse;


public class RegisterDoctorResponse extends BaseResponse<DoctorResource> {
    public RegisterDoctorResponse(String message) {
        super(message);
    }

    public RegisterDoctorResponse(DoctorResource resource) {
        super(resource);
    }
}
