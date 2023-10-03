package com.findJob.service;

import com.findJob.dto.CertificationDTO;
import com.findJob.exception.NotFoundException;

public interface CertificationService {

    CertificationDTO createCertification(Integer userProfileId, CertificationDTO certificationDTO) throws NotFoundException;

    CertificationDTO getCertification(Integer certificationId) throws NotFoundException;

    CertificationDTO updateCertification(Integer certificationId, CertificationDTO certificationDTO) throws NotFoundException;

    CertificationDTO deleteCertification(Integer userProfileId, Integer certificationId) throws NotFoundException;
}

