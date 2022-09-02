package com.example.Sprint1.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sprint1.email.domain.model.EmailValuesDTO;
import com.example.Sprint1.email.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/email/send-html")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO dto) {
        emailService.sendEmailTemplate(dto);
        return new ResponseEntity("Correo con plantilla enviado con éxito", HttpStatus.OK);
    }
}
