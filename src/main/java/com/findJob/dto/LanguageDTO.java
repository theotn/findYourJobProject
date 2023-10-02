package com.findJob.dto;

import com.findJob.enums.LanguageLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class LanguageDTO {
    private Integer id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private LanguageLevel languageLevel;
}
