package com.DoAn1.examservice.domain.requestDTO.omr;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqOmrSectionsDTO {

    @Valid
    private List<ReqOmrAnswerDTO> mcq;

    @Valid
    private List<ReqOmrAnswerDTO> tfq;

    @Valid
    private List<ReqOmrAnswerDTO> saq;
}
