package com.DoAn1.examservice.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DoAn1.examservice.domain.requestDTO.questiongroup.ReqCreateQuestionGroupDTO;
import com.DoAn1.examservice.domain.responseDTO.questiongroup.ResQuestionGroupDTO;
import com.DoAn1.examservice.service.QuestionGroupService;
import com.DoAn1.examservice.util.annotation.ApiMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/question-groups")
@RequiredArgsConstructor
public class QuestionGroupController {

    private final QuestionGroupService questionGroupService;

    @PostMapping
    @ApiMessage("Create question group")
    public ResQuestionGroupDTO createQuestionGroup(@Valid @RequestBody ReqCreateQuestionGroupDTO request) {
        return questionGroupService.createQuestionGroup(request);
    }

    @GetMapping("/{questionGroupUuid}")
    @ApiMessage("Get question group by id")
    public ResQuestionGroupDTO getQuestionGroup(@PathVariable(name = "questionGroupUuid") UUID questionGroupUuid) {
        return questionGroupService.getQuestionGroup(questionGroupUuid);
    }

    @GetMapping
    @ApiMessage("Get question groups")
    public Page<ResQuestionGroupDTO> getQuestionGroups(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "topic", required = false) String topic,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return questionGroupService.getQuestionGroups(name, type, topic, pageable);
    }
}
