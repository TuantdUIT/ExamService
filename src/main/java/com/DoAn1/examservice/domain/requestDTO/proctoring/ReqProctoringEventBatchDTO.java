package com.DoAn1.examservice.domain.requestDTO.proctoring;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqProctoringEventBatchDTO {

    @Valid
    @NotEmpty(message = "Proctoring events must not be empty")
    @Size(max = 100, message = "Proctoring events batch size must not exceed 100")
    private List<ReqProctoringEventDTO> events;
}
