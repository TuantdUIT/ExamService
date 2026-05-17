package com.DoAn1.examservice.domain.responseDTO.attempt;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResAttemptQuestionTrueFalseStatementDTO {
    private UUID statementUuid;
    private Integer statementOrder;
    private String statementContent;
}
