package com.DoAn1.examservice.domain.requestDTO.question;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionActivationDTO {

    @NotNull(message = "Active flag is required")
    private Boolean isActive;
}
