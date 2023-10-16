package com.findJob.service.impl;

import com.findJob.entity.ChatRoom;
import com.findJob.entity.Message;
import com.findJob.entity.User;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.ChatRoomRepository;
import com.findJob.repository.MessageRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.MessageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    private ChatRoomRepository chatRoomRepository;

    private UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message sendMessage(Integer chatRoomId, Integer userId, String content) throws NotFoundException {

        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomId);
        ChatRoom chatRoom = optionalChatRoom.orElseThrow(()-> new NotFoundException("Chat not found!"));

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(()-> new NotFoundException("User not found!"));

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setUser(user);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
        return message;
    }

    @Override
    public List<Message> getAllMessages(Integer chatRoomId) throws NotFoundException {

        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(chatRoomId);
        ChatRoom chatRoom = optionalChatRoom.orElseThrow(()-> new NotFoundException("Chat not found!"));

        List<Message> messages = messageRepository.findByChatRoom(chatRoom);
        return messages;
    }
}
