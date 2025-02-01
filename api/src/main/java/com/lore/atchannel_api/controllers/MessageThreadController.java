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

import com.lore.atchannel_api.models.thread.MessageThreadDTO;
import com.lore.atchannel_api.services.MessageThreadService;
import com.lore.atchannel_api.usecase.Helper;

@RestController
@RequestMapping("/atchannel/api/threads")
public class MessageThreadController {
    
    private MessageThreadService service;

    public MessageThreadController(MessageThreadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllThreads() {
        return ResponseEntity
            .status(200)
            .body(this.service.getAllThreads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getThreadById(@PathVariable("id") String id) {
        var opt_thread = this.service.getThreadById(id);

        if (opt_thread.isPresent())
            return ResponseEntity
                .status(200)
                .body(opt_thread.get());

        return ResponseEntity
            .status(404)
            .body(Helper.NewHttpResponse("message", "thread not found"));
    }

    @PostMapping
    public ResponseEntity<?> createThread(@RequestBody MessageThreadDTO dto) {
        String error = "";
        
        if (dto.getTitle() == null) error = "title is required";
        else if (dto.getContent() == null) error = "content is required";
        
        if (error.isEmpty()) {
            if (this.service.existsByTitle(dto.getTitle()))
                return ResponseEntity
                    .status(400)
                    .body(Helper.NewHttpResponse("message", "thread title already exists"));
            return ResponseEntity
                .status(201)
                .body(this.service.createThread(dto));
        }

        return ResponseEntity
            .status(400)
            .body(Helper.NewHttpResponse("message", error));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateThread(
        @PathVariable("id") String id,
        @RequestBody MessageThreadDTO dto
    ) {
        try {
            return ResponseEntity
                .status(200)
                .body(this.service.updateThreadDTO(id, dto));
        }
        catch (RuntimeException re) {
            return ResponseEntity
                .status(200)
                .body(Helper.NewHttpResponse("message", re.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteThread(@PathVariable("id") String id) {
        if (this.service.existsById(id)) {
            this.service.deleteThread(id);
            return ResponseEntity
                .status(410)
                .body(Helper.NewHttpResponse("message", "thread successfully deleted"));
        }
        
        return ResponseEntity
            .status(404)
            .body(Helper.NewHttpResponse("message", "thread not found"));
    }
    
}