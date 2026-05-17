package com.DoAn1.examservice.domain.responseDTO.attempt;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResAttemptQuestionMcOptionDTO {
    private UUID optionUuid;
    private String optionKey;
    private String optionContent;
}
