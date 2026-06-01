package com.DoAn1.examservice.domain.responseDTO.proctoring;

import java.time.Instant;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.ProctoringEventType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResProctoringEventDTO {
    private UUID eventUuid;
    private UUID attemptUuid;
    private Instant eventTime;
    private ProctoringEventType eventType;
    private String eventPayload;
}
