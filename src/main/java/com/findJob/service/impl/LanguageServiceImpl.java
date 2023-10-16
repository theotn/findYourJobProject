package com.findJob.service.impl;

import com.findJob.dto.LanguageDTO;
import com.findJob.entity.Language;
import com.findJob.entity.UserProfile;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.LanguageRepository;
import com.findJob.repository.UserProfileRepository;
import com.findJob.service.LanguageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    private ModelMapper modelMapper;

    private UserProfileRepository userProfileRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper, UserProfileRepository userProfileRepository) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public LanguageDTO createLanguage(Integer userProfileId, LanguageDTO languageDTO) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Language language = modelMapper.map(languageDTO, Language.class);
        languageRepository.save(language);

        userProfile.getLanguages().add(language);

        return modelMapper.map(language, LanguageDTO.class);
    }

    @Override
    public LanguageDTO getLanguage(Integer languageId) throws NotFoundException {

        Optional<Language> languageOptional = languageRepository.findById(languageId);
        Language language = languageOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        return modelMapper.map(language, LanguageDTO.class);
    }

    @Override
    public LanguageDTO updateLanguage(Integer languageId, LanguageDTO languageDTO) throws NotFoundException {

        Optional<Language> languageOptional = languageRepository.findById(languageId);
        Language language = languageOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        if (languageDTO.getName() != null) language.setName(languageDTO.getName());
        if (languageDTO.getLanguageLevel() != null) language.setLanguageLevel(languageDTO.getLanguageLevel());

        return modelMapper.map(language, LanguageDTO.class);
    }

    @Override
    public LanguageDTO deleteLanguage(Integer userProfileId, Integer languageId) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Optional<Language> languageOptional = languageRepository.findById(languageId);
        Language language = languageOptional.orElseThrow(() -> new NotFoundException("Language Not found!"));

        userProfile.getLanguages().remove(language);
        languageRepository.delete(language);

        return modelMapper.map(language, LanguageDTO.class);
    }
}
