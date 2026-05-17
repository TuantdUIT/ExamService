package com.DoAn1.examservice.domain.requestDTO.exam;

import com.DoAn1.examservice.domain.enums.ExamStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqExamStatusDTO {

    @NotNull(message = "Status is required")
    private ExamStatus status;
}
