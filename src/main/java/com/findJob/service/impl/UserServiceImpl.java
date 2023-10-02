package com.findJob.service.impl;

import com.findJob.dto.UserDTO;
import com.findJob.entity.User;
import com.findJob.enums.Role;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.UserRepository;
import com.findJob.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    private RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws BadRequestException {

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new BadRequestException("Please provide credentials!");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("This email is already used!");
        }

        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setIsActive(true);

        if (userDTO.getRole() == Role.USER) {
            user.setRole(Role.USER);
            userRepository.save(user);
        } else {
            user.setRole(Role.COMPANY);
            userRepository.save(user);
        }

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) throws NotFoundException, BadRequestException {

        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty() || userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new BadRequestException("Please provide credentials!");
        }

        User userRepo = userRepository.findByEmail(userDTO.getEmail());

        if (userRepo != null) {

            if (passwordEncoder.matches(userDTO.getPassword(), userRepo.getPassword())) {

                if (!userRepo.getIsActive()) throw new BadRequestException("This account is disabled!");

                UserDTO userReturn = modelMapper.map(userRepo, UserDTO.class);
                userReturn.setPassword(null);

                return userReturn;
            }
        }

        throw new NotFoundException("The credentials provided are incorrect!");

    }


    @Override
    public UserDTO getUser(Integer userId) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) throws NotFoundException, BadRequestException {

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));

        if (userDTO.getEmail() != null) {

            if (!userRepository.existsByEmail(userDTO.getEmail())) {
                user.setEmail(userDTO.getEmail());
            } else {
                throw new BadRequestException("This email is already used!");
            }

        }

        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        if (userDTO.getIsActive() != null) {
            user.setIsActive(userDTO.getIsActive());
        }

        return modelMapper.map(user, UserDTO.class);
    }

    //DE MODIFICAT
//    @Override
//    public UserDTO disableUser(Integer userId) throws NotFoundException {
//
//        Optional<User> userOptional = userRepository.findById(userId);
//        User user = userOptional.orElseThrow(()-> new NotFoundException("User not found!"));
//
//        user.setIsActive(false0);
//
//        return modelMapper.map(user,UserDTO.class);
//    }

//    @Override
//    public void reportFeedback(Integer userId, Integer feedbackId) throws NotFoundException {
//
//        Optional<User> userOptional = userRepository.findById(userId);
//        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));
//
//        Map<String, Integer> params = new HashMap<>();
//        params.put("feedback", feedbackId);
//        params.put("user", userId);
//        FeedbackDTO feedbackDTO = restTemplate.postForObject("http://localhost:9200/feedback/report?feedback={feedback}&user={user}", new FeedbackDTO(), FeedbackDTO.class, params);
//
//    }
}
