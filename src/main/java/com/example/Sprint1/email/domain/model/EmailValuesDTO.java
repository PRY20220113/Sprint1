package com.example.Sprint1.email.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailValuesDTO {
    private String mailFrom;
    private String mailTo;
    private String subject;
    private String userName;
    private String jwt;
}
