package com.findJob.api;

import com.findJob.entity.ChatRoom;
import com.findJob.exception.BadRequestException;
import com.findJob.exception.NotFoundException;
import com.findJob.service.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    private ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ChatRoom> openChatRoom(@RequestParam("user1") Integer user1, @RequestParam("user2") Integer user2) throws NotFoundException, BadRequestException {

        ChatRoom chatRoom = chatRoomService.openChatRoom(user1,user2);
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getChatRoom(@RequestParam("user") Integer user) throws NotFoundException {

        List<ChatRoom> chatRooms = chatRoomService.getChatRooms(user);
        return new ResponseEntity<>(chatRooms, HttpStatus.CREATED);
    }
 }

