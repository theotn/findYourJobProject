package com.findJob.service;


import com.findJob.dto.UserDTO;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

public interface UserService {

    UserDTO createUser(UserDTO userDTO) throws BadRequestException;
    UserDTO loginUser(UserDTO userDTO) throws NotFoundException, BadRequestException;
    UserDTO getUser(Integer userId) throws NotFoundException, BadRequestException;
    UserDTO updateUser(Integer userId, UserDTO userDTO) throws NotFoundException, BadRequestException;
//    UserDTO disableUser(Integer userId) throws NotFoundException;
   // void reportFeedback(Integer userId, Integer feedbackId) throws NotFoundException;

}
