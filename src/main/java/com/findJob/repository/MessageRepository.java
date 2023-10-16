package com.findJob.repository;

import com.findJob.entity.ChatRoom;
import com.findJob.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    public List<Message> findByChatRoom(ChatRoom chatRoom);
}
