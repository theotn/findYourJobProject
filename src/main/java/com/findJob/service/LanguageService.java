package com.findJob.service;

import com.findJob.dto.LanguageDTO;
import com.findJob.exception.NotFoundException;

public interface LanguageService {

    LanguageDTO createLanguage(LanguageDTO languageDTO);

    LanguageDTO getLanguage(Integer languageId) throws NotFoundException;

    LanguageDTO updateLanguage(Integer languageId, LanguageDTO languageDTO) throws NotFoundException;

    LanguageDTO deleteLanguage(Integer languageId) throws NotFoundException;


}
