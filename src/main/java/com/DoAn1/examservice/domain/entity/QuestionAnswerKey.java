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
@Table(name = "question_answer_key", uniqueConstraints = {
        @UniqueConstraint(name = "uk_question_answer_key_question", columnNames = { "questionUuid" })
}, indexes = {
        @Index(name = "idx_question_answer_key_question", columnList = "questionUuid")
})
public class QuestionAnswerKey {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID answerKeyUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String correctAnswerRaw;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String normalizedAnswer;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void prePersist() {
        if (answerKeyUuid == null) {
            answerKeyUuid = UuidV7Generator.generate();
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }
}

