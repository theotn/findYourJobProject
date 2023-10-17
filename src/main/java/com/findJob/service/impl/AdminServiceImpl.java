package com.findJob.service.impl;

import com.findJob.dto.AdminDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.entity.Admin;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.AdminRepository;
import com.findJob.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {

        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminDTO login(AdminDTO adminDTO) throws NotFoundException {

        Admin admin = adminRepository.findByName(adminDTO.getName());

        if (passwordEncoder.matches(adminDTO.getPassword(), admin.getPassword())) {
            AdminDTO adminReturned = modelMapper.map(admin, AdminDTO.class);
            adminReturned.setPassword(null);
            return adminReturned;
        }

        throw new NotFoundException("The credentials provided are incorrect!");
    }
}
