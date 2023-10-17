package com.findJob.service.impl;

import com.findJob.entity.ChatRoom;
import com.findJob.entity.User;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.repository.ChatRoomRepository;
import com.findJob.repository.UserRepository;
import com.findJob.service.ChatRoomService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService {

    private ChatRoomRepository chatRoomRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserRepository userRepository, ModelMapper modelMapper) {

        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ChatRoom openChatRoom(Integer userId1, Integer userId2) throws NotFoundException, BadRequestException {

        Optional<User> userOptional1 = userRepository.findById(userId1);
        User user1 = userOptional1.orElseThrow(() -> new NotFoundException("User not found!"));

        Optional<User> userOptional2 = userRepository.findById(userId2);
        User user2 = userOptional2.orElseThrow(() -> new NotFoundException("User not found!"));

        List<ChatRoom> chatRoomList = chatRoomRepository.findByUser(user1);
        chatRoomList.forEach(x -> System.out.println(x));

        for (ChatRoom c : chatRoomList) {
            if (c.getUsers().contains(user2)) return c;
        }

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUsers(list);

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    @Override
    public List<ChatRoom> getChatRooms(Integer userId) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new NotFoundException("User not found!"));

        return chatRoomRepository.findByUser(user);
    }
}
