package com.lore.atchannel_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lore.atchannel_api.models.message.Message;
import com.lore.atchannel_api.models.message.MessageDTO;
import com.lore.atchannel_api.models.thread.MessageThread;
import com.lore.atchannel_api.repositories.MessageRepository;
import com.lore.atchannel_api.usecase.VisualIdentifier;

@Service
public class MessageService {

    private MessageRepository repository;
    private MessageThreadService threadService;
    
    MessageService(MessageRepository repository, MessageThreadService threadService) {
        this.repository = repository;
        this.threadService = threadService;
    }

    public List<Message> getAllMessages() {
        return this.repository.findAll();
    }

    public Optional<Message> getMessageById(String id) {
        return this.repository.findById(UUID.fromString(id));
    }

    public boolean existsById(String id) {
        return this.repository.existsById(UUID.fromString(id));
    }

    @Transactional
    public Message createMessage(MessageDTO dto) throws RuntimeException {
        var thread = this.threadService.getThreadById(dto.getThread_id())
            .orElseThrow(() -> new RuntimeException("thread id not found"));

        var message = new Message();
        BeanUtils.copyProperties(dto, message);

        
        message.setThread(thread);
        message.setVisual_id(VisualIdentifier.GenerateId());
        message.setMessage_number(thread.getTotal_messages() + 1);
        
        this.repository.save(message);
        this.threadService.updateThreadMessagesNumber(thread, 1);

        return message;
    }

    @Transactional
    public Message updateMessage(String id, MessageDTO dto) throws RuntimeException {
        var message = this.repository.findById(UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("message not found"));

        BeanUtils.copyProperties(dto, message);
        return this.repository.save(message);
    }

    @Transactional
    public void deleteById(String id, MessageThread thread) {
        this.threadService.updateThreadMessagesNumber(thread, -1);
        this.repository.deleteById(UUID.fromString(id));
    }
    
}