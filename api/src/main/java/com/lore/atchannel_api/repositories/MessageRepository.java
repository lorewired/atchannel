package com.lore.atchannel_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lore.atchannel_api.models.message.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findAll();
    Optional<Message> findById(UUID id);
    Optional<Message> findByThreadId(UUID thread_id);
    Optional<Message> findByNickname(String nickname);
    void deleteById(UUID id);
    
}