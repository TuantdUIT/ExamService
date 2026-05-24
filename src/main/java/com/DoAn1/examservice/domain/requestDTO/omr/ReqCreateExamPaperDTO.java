package com.DoAn1.examservice.domain.requestDTO.omr;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqCreateExamPaperDTO {

    @NotNull(message = "Exam id is required")
    private UUID examUuid;

    @NotBlank(message = "Paper code must not be blank")
    private String paperCode;
}
