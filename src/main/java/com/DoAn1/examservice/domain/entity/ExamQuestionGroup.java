package com.DoAn1.examservice.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.QuestionType;
import com.DoAn1.examservice.util.UuidV7Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_question_group")
public class ExamQuestionGroup {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID eqgUuid;

    @Column(nullable = false)
    private UUID examUuid;

    @Column
    private UUID questionGroupUuid;

    @Column(nullable = false, length = 255)
    private String groupName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuestionType questionType;

    @Column(length = 255)
    private String questionTopic;

    @Column(nullable = false)
    private Integer questionCount;

    @Column(nullable = false)
    private Integer pickQuestionCount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal scorePerQuestion;

    @Column(nullable = false)
    private Integer displayOrder;

    @PrePersist
    void prePersist() {
        if (eqgUuid == null) {
            eqgUuid = UuidV7Generator.generate();
        }
    }
}

