package com.DoAn1.examservice.domain.responseDTO.omr;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResOmrImportDTO {
    private UUID omrImportUuid;
    private UUID examUuid;
    private UUID paperUuid;
    private String paperCode;
    private UUID studentUuid;
    private UUID attemptUuid;
    private String externalSubmissionId;
    private String status;
    private BigDecimal score;
    private Instant importedAt;
}
