package com.bootcamp.microservicemeetup.model;

import javax.validation.constraints.NotEmpty;

public class RegistrationDTO {

    private Integer id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String dateOfRegistration;

    @NotEmpty
    private String registration;

}
