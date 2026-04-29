package com.matthew.student_document_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "app", "Student Document Service",
                "version", "1.0",
                "description", "Public info endpoint"
        );
    }

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "UP");
    }
}