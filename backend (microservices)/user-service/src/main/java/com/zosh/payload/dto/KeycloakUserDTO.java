package com.zosh.payload.dto;

import lombok.Data;

@Data
public class KeycloakUserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
