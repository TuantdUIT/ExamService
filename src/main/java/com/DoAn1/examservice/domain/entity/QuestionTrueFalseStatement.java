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
@Table(name = "question_true_false_statement", uniqueConstraints = {
        @UniqueConstraint(name = "uk_tf_statement_question_order", columnNames = { "questionUuid", "statementOrder" })
}, indexes = {
        @Index(name = "idx_tf_statement_question", columnList = "questionUuid")
})
public class QuestionTrueFalseStatement {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID statementUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @Column(nullable = false)
    private Integer statementOrder;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String statementContent;

    @PrePersist
    void prePersist() {
        if (statementUuid == null) {
            statementUuid = UuidV7Generator.generate();
        }
    }
}

