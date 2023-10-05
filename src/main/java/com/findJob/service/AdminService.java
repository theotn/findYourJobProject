package com.findJob.service;

import com.findJob.dto.AdminDTO;
import com.findJob.dto.FeedbackDTO;
import com.findJob.dto.UserDTO;
import com.findJob.exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    AdminDTO login(AdminDTO adminDTO) throws NotFoundException;
}
