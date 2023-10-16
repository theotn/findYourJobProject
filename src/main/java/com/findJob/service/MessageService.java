package com.findJob.service;

import com.findJob.entity.Message;
import com.findJob.exception.NotFoundException;

import java.util.List;

public interface MessageService {

    public Message sendMessage(Integer chatRoomId, Integer userId, String content) throws NotFoundException;

    public List<Message> getAllMessages(Integer chatRoomId) throws NotFoundException;
}
