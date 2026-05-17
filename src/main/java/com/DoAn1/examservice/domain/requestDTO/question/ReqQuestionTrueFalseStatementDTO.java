package com.DoAn1.examservice.domain.requestDTO.question;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionTrueFalseStatementDTO {

    @NotNull(message = "Statement order is required")
    @Min(value = 1, message = "Statement order must be from 1 to 4")
    @Max(value = 4, message = "Statement order must be from 1 to 4")
    private Integer statementOrder;

    @NotBlank(message = "Statement content must not be blank")
    private String statementContent;
}
