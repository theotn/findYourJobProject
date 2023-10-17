package com.findJob.entity;

import com.findJob.enums.LanguageLevel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private LanguageLevel languageLevel;
}
