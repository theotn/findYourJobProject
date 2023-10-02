package com.findJob.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificationDTO {

    private Integer id;

    private String institution;

    private String name;

    private LocalDate startDate;

    private LocalDate expirationDate;

    private String city;
}
