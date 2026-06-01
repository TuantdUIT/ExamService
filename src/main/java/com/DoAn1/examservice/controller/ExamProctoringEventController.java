package com.DoAn1.examservice.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DoAn1.examservice.domain.requestDTO.proctoring.ReqProctoringEventBatchDTO;
import com.DoAn1.examservice.domain.responseDTO.proctoring.ResProctoringEventBatchDTO;
import com.DoAn1.examservice.service.ExamProctoringEventService;
import com.DoAn1.examservice.util.annotation.ApiMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExamProctoringEventController {

    private final ExamProctoringEventService examProctoringEventService;

    @PostMapping("/api/v1/student/attempts/{attemptUuid}/proctoring-events/batch")
    @ApiMessage("Create proctoring events")
    public ResProctoringEventBatchDTO createEvents(
            @PathVariable(name = "attemptUuid") UUID attemptUuid,
            @Valid @RequestBody ReqProctoringEventBatchDTO request) {
        return examProctoringEventService.createEvents(attemptUuid, request);
    }
}
