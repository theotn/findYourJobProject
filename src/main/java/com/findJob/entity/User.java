package com.findJob.entity;

import com.findJob.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private Boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}

