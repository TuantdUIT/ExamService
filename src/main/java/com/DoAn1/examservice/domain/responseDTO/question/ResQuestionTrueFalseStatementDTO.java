package com.DoAn1.examservice.domain.responseDTO.question;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResQuestionTrueFalseStatementDTO {
    private UUID statementUuid;
    private Integer statementOrder;
    private String statementContent;
}
