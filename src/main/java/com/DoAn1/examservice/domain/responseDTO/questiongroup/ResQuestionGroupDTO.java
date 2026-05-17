package com.DoAn1.examservice.domain.responseDTO.questiongroup;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.QuestionType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResQuestionGroupDTO {
    private UUID questionGroupUuid;
    private String groupName;
    private QuestionType questionType;
    private String questionTopic;
    private Integer questionCount;
    private UUID createdByUserUuid;
    private List<ResQuestionGroupItemDTO> items;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
