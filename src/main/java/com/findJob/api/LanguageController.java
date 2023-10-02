package com.findJob.api;

import com.findJob.dto.LanguageDTO;
import com.findJob.exception.NotFoundException;
import com.findJob.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/language")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> createLanguage(@RequestBody LanguageDTO languageDTO){

        LanguageDTO education = languageService.createLanguage(languageDTO);
        return new ResponseEntity<>(education, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<LanguageDTO> getLanguage(@RequestParam("language") Integer languageId) throws NotFoundException {

        LanguageDTO education = languageService.getLanguage(languageId);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<LanguageDTO> updateLanguage(@RequestParam("language") Integer languageId,@RequestBody LanguageDTO languageDTO) throws NotFoundException {

        LanguageDTO education = languageService.updateLanguage(languageId,languageDTO);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<LanguageDTO> deleteLanguage(@RequestParam("language") Integer languageId) throws NotFoundException {

        LanguageDTO education = languageService.deleteLanguage(languageId);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

}
