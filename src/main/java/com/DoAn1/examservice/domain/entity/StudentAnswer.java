package com.DoAn1.examservice.domain.entity;

import java.time.Instant;
import java.util.UUID;

import com.DoAn1.examservice.util.UuidV7Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "student_answer", uniqueConstraints = {
        @UniqueConstraint(name = "uk_student_answer_attempt_question_attempt_no", columnNames = {
                "attemptUuid", "questionUuid", "questionAttemptNumber" })
}, indexes = {
        @Index(name = "idx_student_answer_attempt_question_final", columnList = "attemptUuid,questionUuid,isFinalAnswer")
})
public class StudentAnswer {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID studentAnswerUuid;

    @Column(nullable = false)
    private UUID attemptUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @Column(columnDefinition = "TEXT")
    private String rawAnswer;

    @Column(columnDefinition = "TEXT")
    private String normalizedAnswer;

    @Column(nullable = false)
    private Instant answeredAt;

    @Column(nullable = false)
    private Integer questionAttemptNumber;

    @Column(nullable = false)
    private Boolean isFinalAnswer = false;

    @PrePersist
    void prePersist() {
        if (studentAnswerUuid == null) {
            studentAnswerUuid = UuidV7Generator.generate();
        }
        if (answeredAt == null) {
            answeredAt = Instant.now();
        }
    }
}

