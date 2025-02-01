package com.lore.atchannel_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lore.atchannel_api.models.thread.MessageThread;

@Repository
public interface MessageThreadRepository extends JpaRepository<MessageThread, UUID> {
    
    Optional<MessageThread> findById(UUID thread_id);
    boolean existsByTitle(String title);
    boolean existsById(UUID id);
    @Query("SELECT m FROM MessageThread m ORDER BY m.created_at DESC")
    List<MessageThread> findAllByOrderByCreated_atDesc();

}
