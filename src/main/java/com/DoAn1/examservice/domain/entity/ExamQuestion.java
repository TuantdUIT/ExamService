package com.DoAn1.examservice.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.ExamQuestionSourceType;
import com.DoAn1.examservice.domain.enums.QuestionType;
import com.DoAn1.examservice.util.UuidV7Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_question", uniqueConstraints = {
        @UniqueConstraint(name = "uk_exam_question_exam_order", columnNames = { "examUuid", "questionOrder" })
}, indexes = {
        @Index(name = "idx_exam_question_exam_order", columnList = "examUuid,questionOrder")
})
public class ExamQuestion {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID examQuestionUuid;

    @Column(nullable = false)
    private UUID examUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @Column(nullable = false)
    private Integer questionOrder;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private QuestionType sectionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExamQuestionSourceType sourceType;

    @PrePersist
    void prePersist() {
        if (examQuestionUuid == null) {
            examQuestionUuid = UuidV7Generator.generate();
        }
    }
}

