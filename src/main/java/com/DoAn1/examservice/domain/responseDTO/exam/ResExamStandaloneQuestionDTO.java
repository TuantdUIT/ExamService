package com.DoAn1.examservice.domain.responseDTO.exam;

import java.math.BigDecimal;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.ExamQuestionSourceType;
import com.DoAn1.examservice.domain.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResExamStandaloneQuestionDTO {
    private UUID examQuestionUuid;
    private UUID questionUuid;
    private Integer questionOrder;
    private BigDecimal score;
    private QuestionType sectionType;
    private ExamQuestionSourceType sourceType;
    private ResExamQuestionDetailDTO questionDetail;
}
