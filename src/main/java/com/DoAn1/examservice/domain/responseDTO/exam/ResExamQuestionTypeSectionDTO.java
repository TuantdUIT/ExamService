package com.DoAn1.examservice.domain.responseDTO.exam;

import java.util.List;

import com.DoAn1.examservice.domain.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResExamQuestionTypeSectionDTO {
    private QuestionType questionType;
    private Integer totalQuestionCount;
    private List<ResExamStandaloneQuestionDTO> standaloneQuestions;
    private List<ResExamQuestionGroupDTO> groups;
}
