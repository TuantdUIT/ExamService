package com.DoAn1.examservice.domain.requestDTO.omr;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqOmrAnswerDTO {

    @NotNull(message = "Question order is required")
    private Integer questionOrder;

    private String rawAnswer;
}
