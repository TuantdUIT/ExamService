package com.DoAn1.examservice.domain.requestDTO.exam;

import java.math.BigDecimal;
import java.util.UUID;

import com.DoAn1.examservice.domain.requestDTO.questiongroup.ReqCreateQuestionGroupDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqExamQuestionGroupDTO {

    private UUID questionGroupUuid;

    @Valid
    private ReqCreateQuestionGroupDTO newQuestionGroup;

    @NotNull(message = "Pick question count is required")
    @Min(value = 1, message = "Pick question count must be at least 1")
    private Integer pickQuestionCount;

    @NotNull(message = "Score per question is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Score per question must be greater than 0")
    private BigDecimal scorePerQuestion;

    @NotNull(message = "Display order is required")
    @Min(value = 1, message = "Display order must be at least 1")
    private Integer displayOrder;
}
