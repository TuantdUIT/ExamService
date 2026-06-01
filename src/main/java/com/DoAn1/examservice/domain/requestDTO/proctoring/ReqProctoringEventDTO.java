package com.DoAn1.examservice.domain.requestDTO.proctoring;

import java.time.Instant;

import com.DoAn1.examservice.domain.enums.ProctoringEventType;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqProctoringEventDTO {

    private Instant eventTime;

    @NotNull(message = "Proctoring event type is required")
    private ProctoringEventType eventType;

    private JsonNode eventPayload;
}
