package com.DoAn1.examservice.domain.responseDTO.exam;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResExamQuestionGroupDTO {
    private UUID eqgUuid;
    private UUID questionGroupUuid;
    private String groupName;
    private QuestionType questionType;
    private String questionTopic;
    private Integer poolQuestionCount;
    private Integer pickQuestionCount;
    private BigDecimal scorePerQuestion;
    private Integer displayOrder;
    private List<ResExamQuestionGroupItemDTO> items;
}
