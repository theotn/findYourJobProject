package com.findJob.service.impl;

import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.repository.AdminRepository;
import com.findJob.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private RestTemplate restTemplate;

    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, RestTemplate restTemplate) {

        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FeedbackDTO> getFeedbackReported() {

        FeedbackDTO[] feedbackDTOList = restTemplate.getForObject("http://localhost:9200/feedback/reports", FeedbackDTO[].class);
        List<FeedbackDTO> list =  Arrays.asList(feedbackDTOList);

        return list;
    }

    @Override
    public UserDTO changeUserStatus(Integer userId, UserDTO userDTO) throws IOException {

        HttpEntity<UserDTO> httpEntity = new HttpEntity<>(userDTO);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange("http://localhost:8000/user?user="+userId, HttpMethod.PUT, httpEntity, UserDTO.class);

        return responseEntity.getBody();
    }
}
