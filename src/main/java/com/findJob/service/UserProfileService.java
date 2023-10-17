package com.findJob.service;

import com.findJob.dto.*;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

public interface UserProfileService {

    UserProfileDTO createUserProfile(UserDTO userDTO) throws BadRequestException;

    UserProfileDTO getUserProfile(Integer userId) throws NotFoundException, BadRequestException;

    UserProfileDTO updateUserProfile(Integer userProfileId, UserProfileDTO userProfileDTO) throws NotFoundException;

}
