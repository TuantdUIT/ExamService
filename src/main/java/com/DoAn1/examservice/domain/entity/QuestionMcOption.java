package com.DoAn1.examservice.domain.entity;

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
@Table(name = "question_mc_option", uniqueConstraints = {
        @UniqueConstraint(name = "uk_question_mc_option_question_key", columnNames = { "questionUuid", "optionKey" })
}, indexes = {
        @Index(name = "idx_question_mc_option_question", columnList = "questionUuid")
})
public class QuestionMcOption {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID optionUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @Column(nullable = false, length = 1)
    private String optionKey;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String optionContent;

    @PrePersist
    void prePersist() {
        if (optionUuid == null) {
            optionUuid = UuidV7Generator.generate();
        }
    }
}

