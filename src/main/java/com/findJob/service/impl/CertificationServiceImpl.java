package com.findJob.service.impl;

import com.findJob.dto.CertificationDTO;
import com.findJob.entity.Certification;
import com.findJob.entity.UserProfile;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.CertificationRepository;
import com.findJob.repository.UserProfileRepository;
import com.findJob.service.CertificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CertificationServiceImpl implements CertificationService {

    private CertificationRepository certificationRepository;
    private ModelMapper modelMapper;

    private UserProfileRepository userProfileRepository;

    public CertificationServiceImpl(CertificationRepository certificationRepository, ModelMapper modelMapper, UserProfileRepository userProfileRepository) {
        this.certificationRepository = certificationRepository;
        this.modelMapper = modelMapper;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public CertificationDTO createCertification(Integer userProfileId, CertificationDTO certificationDTO) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Certification certification = modelMapper.map(certificationDTO, Certification.class);
        certificationRepository.save(certification);

        userProfile.getCertifications().add(certification);

        return modelMapper.map(certification, CertificationDTO.class);
    }

    @Override
    public CertificationDTO getCertification(Integer certificationId) throws NotFoundException {

        Optional<Certification> certificationOptional = certificationRepository.findById(certificationId);
        Certification certification = certificationOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        return modelMapper.map(certification, CertificationDTO.class);
    }

    @Override
    public CertificationDTO updateCertification(Integer certificationId, CertificationDTO certificationDTO) throws NotFoundException {

        Optional<Certification> certificationOptional = certificationRepository.findById(certificationId);
        Certification certification = certificationOptional.orElseThrow(() -> new NotFoundException("Not found!"));

        if (certificationDTO.getInstitution() != null) certification.setInstitution(certificationDTO.getInstitution());
        if (certificationDTO.getName() != null) certification.setName(certificationDTO.getName());
        if (certificationDTO.getStartDate() != null) certification.setStartDate(certificationDTO.getStartDate());
        if (certificationDTO.getExpirationDate() != null)
            certification.setExpirationDate(certificationDTO.getExpirationDate());
        if (certificationDTO.getCity() != null) certification.setCity(certificationDTO.getCity());

        return modelMapper.map(certification, CertificationDTO.class);
    }

    @Override
    public CertificationDTO deleteCertification(Integer userProfileId, Integer certificationId) throws NotFoundException {

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        UserProfile userProfile = optionalUserProfile.orElseThrow(() -> new NotFoundException("Profile not found!"));

        Optional<Certification> certificationOptional = certificationRepository.findById(certificationId);
        Certification certification = certificationOptional.orElseThrow(() -> new NotFoundException("Certification Not found!"));

        userProfile.getCertifications().remove(certification);
        certificationRepository.delete(certification);

        return modelMapper.map(certification, CertificationDTO.class);
    }
}
