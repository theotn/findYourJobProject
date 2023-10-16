package com.findJob.api;

import com.findJob.dto.AdminDTO;
import com.findJob.exception.NotFoundException;
import com.findJob.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<AdminDTO> login(@RequestBody AdminDTO adminDTO) throws NotFoundException {

        AdminDTO admin = adminService.login(adminDTO);
        return new ResponseEntity<>(admin, HttpStatus.OK);

    }
}
