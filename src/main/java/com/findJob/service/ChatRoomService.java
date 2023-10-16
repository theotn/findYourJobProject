package com.findJob.service;

import com.findJob.entity.ChatRoom;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;

import java.util.List;

public interface ChatRoomService {

    public ChatRoom openChatRoom(Integer userId1, Integer userId2) throws NotFoundException, BadRequestException;

    public List<ChatRoom> getChatRooms(Integer userId) throws NotFoundException;
}
