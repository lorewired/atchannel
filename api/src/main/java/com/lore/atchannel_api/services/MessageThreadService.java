package com.lore.atchannel_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lore.atchannel_api.models.thread.MessageThread;
import com.lore.atchannel_api.models.thread.MessageThreadDTO;
import com.lore.atchannel_api.repositories.MessageThreadRepository;

@Service
public class MessageThreadService {

    private MessageThreadRepository repository;

    public MessageThreadService(MessageThreadRepository repository) {
        this.repository = repository;
    }
    
    public List<MessageThread> getAllThreads() {
        return this.repository.findAllByOrderByCreated_atDesc();
    }
    
    public Optional<MessageThread> getThreadById(String thread_id) {
        return this.repository.findById(UUID.fromString(thread_id));
    }

    public boolean existsByTitle(String title) {
        return this.repository.existsByTitle(title);
        
    }
    public boolean existsById(String id) {
        return this.repository.existsById(UUID.fromString(id));
    }

    @Transactional
    public MessageThread createThread(MessageThreadDTO dto) {
        var thread = new MessageThread();
        BeanUtils.copyProperties(dto, thread);
        thread.setTotal_messages(0);
        return this.repository.save(thread);
    }

    @Transactional
    public MessageThread updateThread(MessageThread thread) {
        return this.repository.saveAndFlush(thread);
    }

    @Transactional
    public MessageThread updateThreadDTO(String id, MessageThreadDTO dto) throws RuntimeException {
        var thread = this.repository.findById(UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("thread id not found"));

        BeanUtils.copyProperties(dto, thread);
        return this.repository.save(thread);
    }

    @Transactional
    public void deleteThread(String id) {
        this.repository.deleteById(UUID.fromString(id));
    }

    @Transactional
    public void updateThreadMessagesNumber(MessageThread thread, int to_add) {
        thread.setTotal_messages(thread.getTotal_messages() + to_add);
        this.repository.saveAndFlush(thread);
    }

}