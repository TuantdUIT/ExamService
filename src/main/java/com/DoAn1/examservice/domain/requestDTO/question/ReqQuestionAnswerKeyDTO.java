package com.DoAn1.examservice.domain.requestDTO.question;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionAnswerKeyDTO {

    @NotBlank(message = "Correct answer raw must not be blank")
    private String correctAnswerRaw;
}
