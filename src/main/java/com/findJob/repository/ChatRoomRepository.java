package com.findJob.repository;

import com.findJob.entity.ChatRoom;
import com.findJob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Integer> {

    @Query("Select c from ChatRoom c where :user IN (Select c2.users from ChatRoom c2 where c2.id=c.id)")
    List<ChatRoom> findByUser(User user);
}
