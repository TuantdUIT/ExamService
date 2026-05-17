package com.DoAn1.examservice.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DoAn1.examservice.domain.requestDTO.attempt.ReqStudentAnswerDTO;
import com.DoAn1.examservice.domain.responseDTO.attempt.ResExamAttemptDTO;
import com.DoAn1.examservice.domain.responseDTO.attempt.ResExamAttemptSummaryDTO;
import com.DoAn1.examservice.service.ExamAttemptService;
import com.DoAn1.examservice.util.annotation.ApiMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExamAttemptController {

    private final ExamAttemptService examAttemptService;

    @PostMapping("/api/v1/student/exams/{examUuid}/attempts")
    @ApiMessage("Start exam attempt")
    public ResExamAttemptDTO startAttempt(@PathVariable(name = "examUuid") UUID examUuid) {
        return examAttemptService.startAttempt(examUuid);
    }

    @GetMapping("/api/v1/student/attempts/{attemptUuid}")
    @ApiMessage("Get exam attempt")
    public ResExamAttemptDTO getAttempt(@PathVariable(name = "attemptUuid") UUID attemptUuid) {
        return examAttemptService.getAttempt(attemptUuid);
    }

    @GetMapping("/api/v1/student/attempts")
    @ApiMessage("Get student attempts")
    public Page<ResExamAttemptSummaryDTO> getAttempts(
            @RequestParam(name = "examUuid", required = false) UUID examUuid,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return examAttemptService.getAttempts(examUuid, pageable);
    }

    @PostMapping("/api/v1/student/attempts/{attemptUuid}/answers")
    @ApiMessage("Save student answer")
    public ResExamAttemptDTO saveAnswer(
            @PathVariable(name = "attemptUuid") UUID attemptUuid,
            @Valid @RequestBody ReqStudentAnswerDTO request) {
        return examAttemptService.saveAnswer(attemptUuid, request);
    }

    @PostMapping("/api/v1/student/attempts/{attemptUuid}/submit")
    @ApiMessage("Submit exam attempt")
    public ResExamAttemptDTO submitAttempt(@PathVariable(name = "attemptUuid") UUID attemptUuid) {
        return examAttemptService.submitAttempt(attemptUuid);
    }
}
