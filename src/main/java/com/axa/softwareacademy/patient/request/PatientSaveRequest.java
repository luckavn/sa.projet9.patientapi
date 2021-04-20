package com.axa.softwareacademy.patient.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSaveRequest {

    private String familyName;
    private String givenName;
    private String address;
    private Date dob;
    private String sex;
    private String phone;
    private Integer age;
}
