package com.DoAn1.examservice.domain.responseDTO.questiongroup;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResQuestionGroupItemDTO {
    private UUID questionGroupItemUuid;
    private UUID questionUuid;
    private ResQuestionGroupQuestionDetailDTO questionDetail;
}
