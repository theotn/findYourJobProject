package com.findJob.dto;

import com.findJob.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    @Email(message = "{user.email.invalid}")
    private String email;

    @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}",message = "{user.password.invalid}")
    private String password;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;
}
