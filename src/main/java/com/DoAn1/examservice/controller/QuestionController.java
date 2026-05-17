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

import com.DoAn1.examservice.domain.requestDTO.question.ReqCreateQuestionDTO;
import com.DoAn1.examservice.domain.requestDTO.question.ReqQuestionActivationDTO;
import com.DoAn1.examservice.domain.requestDTO.question.ReqUpdateQuestionDTO;
import com.DoAn1.examservice.domain.responseDTO.question.ResQuestionDTO;
import com.DoAn1.examservice.service.QuestionService;
import com.DoAn1.examservice.util.annotation.ApiMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @ApiMessage("Create question")
    public ResQuestionDTO createQuestion(@Valid @RequestBody ReqCreateQuestionDTO request) {
        return questionService.createQuestion(request);
    }

    @GetMapping("/{questionUuid}")
    @ApiMessage("Get question by id")
    public ResQuestionDTO getQuestion(@PathVariable(name = "questionUuid") UUID questionUuid) {
        return questionService.getQuestion(questionUuid);
    }

    @GetMapping
    @ApiMessage("Get questions")
    public Page<ResQuestionDTO> getQuestions(
            @RequestParam(name = "gradeId", required = false) Long gradeId,
            @RequestParam(name = "topic", required = false) String topic,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "isActive", required = false) Boolean isActive,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return questionService.getQuestions(gradeId, topic, content, type, isActive, pageable);
    }

    @PutMapping("/{questionUuid}")
    @ApiMessage("Update question")
    public ResQuestionDTO updateQuestion(@PathVariable(name = "questionUuid") UUID questionUuid,
            @Valid @RequestBody ReqUpdateQuestionDTO request) {
        return questionService.updateQuestion(questionUuid, request);
    }

    @PatchMapping("/{questionUuid}/activation")
    @ApiMessage("Update question activation")
    public ResQuestionDTO updateQuestionActivation(@PathVariable(name = "questionUuid") UUID questionUuid,
            @Valid @RequestBody ReqQuestionActivationDTO request) {
        return questionService.updateQuestionActivation(questionUuid, request);
    }
}
