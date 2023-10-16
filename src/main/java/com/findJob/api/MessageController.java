package com.findJob.api;

import com.findJob.entity.ChatRoom;
import com.findJob.entity.Message;
import com.findJob.exception.NotFoundException;
import com.findJob.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestParam("chatRoom") Integer chatRoomId, @RequestParam("user") Integer userId, @RequestBody Map<String,String> message) throws NotFoundException {

        Message m = messageService.sendMessage(chatRoomId,userId,message.get("message"));
        return new ResponseEntity<>(m, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages(@RequestParam("chatRoom") Integer chatRoomId) throws NotFoundException {

        List<Message> messages = messageService.getAllMessages(chatRoomId);
        return new ResponseEntity<>(messages, HttpStatus.CREATED);
    }
}
