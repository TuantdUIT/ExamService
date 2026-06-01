package com.DoAn1.examservice.domain.responseDTO.proctoring;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResProctoringEventBatchDTO {
    private UUID attemptUuid;
    private Integer acceptedCount;
    private List<ResProctoringEventDTO> events;
}
