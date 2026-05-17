package com.DoAn1.examservice.domain.responseDTO.exam;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResExamQuestionSummaryDTO {
    private Integer mcqCount;
    private Integer tfqCount;
    private Integer saqCount;
}
