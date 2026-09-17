package com.zosh.payload.dto;

import com.zosh.domain.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;

}
