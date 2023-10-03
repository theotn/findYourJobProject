package com.findJob.service;

import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    List<FeedbackDTO> getFeedbackReported();

    UserDTO changeUserStatus(Integer userId, UserDTO userDTO) throws IOException;
}
