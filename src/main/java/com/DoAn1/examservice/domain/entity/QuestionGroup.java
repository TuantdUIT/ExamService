package com.DoAn1.examservice.domain.entity;

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
@Table(name = "question_group")
public class QuestionGroup extends AuditableEntity {

    @Id
    @Column(nullable = false, updatable = false)
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

    @Column(nullable = false, updatable = false)
    private UUID createdByUserUuid;

    @PrePersist
    void prePersist() {
        if (questionGroupUuid == null) {
            questionGroupUuid = UuidV7Generator.generate();
        }
    }
}
