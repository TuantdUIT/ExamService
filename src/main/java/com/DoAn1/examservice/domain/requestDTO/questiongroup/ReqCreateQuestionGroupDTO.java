package com.DoAn1.examservice.domain.requestDTO.questiongroup;

import java.util.List;

import com.DoAn1.examservice.domain.enums.QuestionType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqCreateQuestionGroupDTO {

    @NotBlank(message = "Group name must not be blank")
    private String groupName;

    @NotNull(message = "Question type is required")
    private QuestionType questionType;

    private String questionTopic;

    @NotNull(message = "Question count is required")
    @Min(value = 1, message = "Question count must be at least 1")
    private Integer questionCount;

    @Valid
    @NotNull(message = "Group items are required")
    private List<ReqQuestionGroupItemDTO> items;
}
