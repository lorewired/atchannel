package com.lore.atchannel_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lore.atchannel_api.models.message.MessageDTO;
import com.lore.atchannel_api.services.MessageService;
import com.lore.atchannel_api.usecase.Helper;

@RestController
@RequestMapping("/atchannel/api/messages")
public class MessageController {

    private MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity
            .status(200)
            .body(this.service.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable("id") String id) {
        var opt_message = this.service.getMessageById(id);
        
        if (opt_message.isEmpty())
            return ResponseEntity
                .status(404)
                .body(Helper.NewHttpResponse("message", "id not found"));
                
        return ResponseEntity
            .status(200)
            .body(opt_message.get());
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO dto) {
        String error = "";

        if (dto.getThread_id() == null) error = "thread id is required";
        else if (dto.getNickname() == null) error = "nickname is required";
        else if (dto.getContent() == null) error = "content is required";
        
        if (!error.isEmpty())
            return ResponseEntity
                .status(400)
                .body(Helper.NewHttpResponse("message", error));
 
        try {
            return ResponseEntity
                .status(201)
                .body(this.service.createMessage(dto));
        } catch (RuntimeException re) {
            return ResponseEntity
                .status(404)
                .body(Helper.NewHttpResponse("message", "Thread not found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(
        @PathVariable("id") String id,
        @RequestBody MessageDTO dto
    ) {
        try {
            var updated_message = this.service.updateMessage(id, dto);
            return ResponseEntity
                .status(200)
                .body(updated_message);
        }
        catch (RuntimeException re) {
            return ResponseEntity
                .status(404)
                .body(Helper.NewHttpResponse("message", re.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable("id") String id) {
        var message = this.service.getMessageById(id);
        if (message.isPresent()) {
            this.service.deleteById(id, message.get().getThread());
            return ResponseEntity
                .status(410)
                .body(Helper.NewHttpResponse("message", "message successfully deleted"));
        }
        return ResponseEntity
            .status(404)
            .body(Helper.NewHttpResponse("message", "message not found"));
    }

}