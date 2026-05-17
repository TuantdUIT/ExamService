package com.DoAn1.examservice.domain.requestDTO.exam;

import java.math.BigDecimal;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.ExamQuestionSourceType;
import com.DoAn1.examservice.domain.enums.QuestionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqExamQuestionDTO {

    @NotNull(message = "Question id is required")
    private UUID questionUuid;

    @NotNull(message = "Question order is required")
    @Min(value = 1, message = "Question order must be at least 1")
    private Integer questionOrder;

    @NotNull(message = "Score is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Score must be greater than 0")
    private BigDecimal score;

    @NotNull(message = "Section type is required")
    private QuestionType sectionType;

    @NotNull(message = "Source type is required")
    private ExamQuestionSourceType sourceType;
}
