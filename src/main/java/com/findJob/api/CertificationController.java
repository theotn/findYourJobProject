package com.findJob.api;


import com.findJob.dto.CertificationDTO;
import com.findJob.exception.NotFoundException;
import com.findJob.service.CertificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certification")
public class CertificationController {

    private CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }
    @PostMapping
    public ResponseEntity<CertificationDTO> createCertification(@RequestParam("userProfile") Integer userProfileId, @RequestBody CertificationDTO certificationDTO) throws NotFoundException {
        CertificationDTO certification = certificationService.createCertification(userProfileId, certificationDTO);
        return new ResponseEntity<>(certification, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<CertificationDTO> getCertification(@RequestParam("certification") Integer certificationId) throws NotFoundException {
        CertificationDTO certificationDTO = certificationService.getCertification(certificationId);
        return new ResponseEntity<>(certificationDTO, HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<CertificationDTO> updateCertification(@RequestParam("certification") Integer certificationId, @RequestBody CertificationDTO certificationDTO) throws NotFoundException {
        CertificationDTO certification = certificationService.updateCertification(certificationId, certificationDTO);
        return new ResponseEntity<>(certification, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<CertificationDTO> deleteCertification(@RequestParam("userProfile") Integer userProfileId, @RequestParam("certification") Integer certificationId) throws NotFoundException {
        CertificationDTO certificationDTO = certificationService.deleteCertification(userProfileId, certificationId);
        return new ResponseEntity<>(certificationDTO, HttpStatus.OK);
    }
}
