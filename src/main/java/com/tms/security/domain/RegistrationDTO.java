package com.tms.security.domain;

import lombok.Data;

@Data
public class RegistrationDTO {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;

    private String userLogin;

    private String userPassword;
}
