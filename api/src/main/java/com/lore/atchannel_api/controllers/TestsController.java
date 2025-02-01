package com.lore.atchannel_api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lore.atchannel_api.usecase.VisualIdentifier;

@RestController
@RequestMapping("atchannel/api/test")
public class TestsController {
    
    @GetMapping("/gen-id")
    ResponseEntity<?> GenerateId() {
        Map<String, String> response = new HashMap<>();
        response.put("generated id", VisualIdentifier.GenerateId());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
    }

    

}
