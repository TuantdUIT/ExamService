package com.DoAn1.examservice.domain.requestDTO.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionMcOptionDTO {

    @NotBlank(message = "Option key must not be blank")
    @Pattern(regexp = "^[A-D]$", message = "Option key must be one of A, B, C, D")
    private String optionKey;

    @NotBlank(message = "Option content must not be blank")
    private String optionContent;
}
