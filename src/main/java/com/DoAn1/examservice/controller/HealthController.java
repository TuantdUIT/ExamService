package com.DoAn1.examservice.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoAn1.examservice.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping("/health")
    @ApiMessage("Exam service is healthy")
    public Map<String, Object> health() {
        return Map.of(
                "service", "ExamService",
                "status", "UP",
                "timestamp", Instant.now().toString());
    }
}

