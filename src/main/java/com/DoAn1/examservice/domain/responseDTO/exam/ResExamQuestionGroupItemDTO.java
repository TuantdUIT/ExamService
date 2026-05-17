package com.DoAn1.examservice.domain.responseDTO.exam;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResExamQuestionGroupItemDTO {
    private UUID eqgiUuid;
    private UUID questionUuid;
    private ResExamQuestionDetailDTO questionDetail;
}
