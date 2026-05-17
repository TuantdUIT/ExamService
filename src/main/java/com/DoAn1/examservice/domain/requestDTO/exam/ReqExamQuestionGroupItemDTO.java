package com.DoAn1.examservice.domain.requestDTO.exam;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqExamQuestionGroupItemDTO {

    @NotNull(message = "Question id is required")
    private UUID questionUuid;
}
