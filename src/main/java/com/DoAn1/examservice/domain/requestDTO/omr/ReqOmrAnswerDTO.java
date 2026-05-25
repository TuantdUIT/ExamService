package com.DoAn1.examservice.domain.requestDTO.omr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqOmrAnswerDTO {

    @NotNull(message = "Section question number is required")
    private Integer sectionQuestionNumber;

    private String rawAnswer;
}
