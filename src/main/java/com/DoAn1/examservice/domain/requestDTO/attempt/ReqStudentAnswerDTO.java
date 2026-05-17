package com.DoAn1.examservice.domain.requestDTO.attempt;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqStudentAnswerDTO {

    @NotNull(message = "Question id is required")
    private UUID questionUuid;

    private String rawAnswer;
}
