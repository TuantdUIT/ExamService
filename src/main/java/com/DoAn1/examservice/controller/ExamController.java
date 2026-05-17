package com.DoAn1.examservice.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DoAn1.examservice.domain.requestDTO.exam.ReqCreateExamDTO;
import com.DoAn1.examservice.domain.requestDTO.exam.ReqExamStatusDTO;
import com.DoAn1.examservice.domain.requestDTO.exam.ReqUpdateExamDTO;
import com.DoAn1.examservice.domain.responseDTO.exam.ResExamDTO;
import com.DoAn1.examservice.service.ExamService;
import com.DoAn1.examservice.util.annotation.ApiMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    @ApiMessage("Create exam")
    public ResExamDTO createExam(@Valid @RequestBody ReqCreateExamDTO request) {
        return examService.createExam(request);
    }

    @GetMapping("/{examUuid}")
    @ApiMessage("Get exam by id")
    public ResExamDTO getExam(@PathVariable(name = "examUuid") UUID examUuid) {
        return examService.getExam(examUuid);
    }

    @GetMapping
    @ApiMessage("Get exams")
    public Page<ResExamDTO> getExams(
            @RequestParam(name = "gradeId", required = false) Long gradeId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "status", required = false) String status,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return examService.getExams(gradeId, name, type, status, pageable);
    }

    @PutMapping("/{examUuid}")
    @ApiMessage("Update exam")
    public ResExamDTO updateExam(@PathVariable(name = "examUuid") UUID examUuid,
            @Valid @RequestBody ReqUpdateExamDTO request) {
        return examService.updateExam(examUuid, request);
    }

    @PatchMapping("/{examUuid}/status")
    @ApiMessage("Update exam status")
    public ResExamDTO updateExamStatus(@PathVariable(name = "examUuid") UUID examUuid,
            @Valid @RequestBody ReqExamStatusDTO request) {
        return examService.updateExamStatus(examUuid, request);
    }
}
