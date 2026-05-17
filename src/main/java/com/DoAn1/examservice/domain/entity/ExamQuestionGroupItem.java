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
@Table(name = "exam_question_group_item", uniqueConstraints = {
        @UniqueConstraint(name = "uk_eqg_item_group_question", columnNames = { "eqgUuid", "questionUuid" })
}, indexes = {
        @Index(name = "idx_eqg_item_group", columnList = "eqgUuid")
})
public class ExamQuestionGroupItem {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID eqgiUuid;

    @Column(nullable = false)
    private UUID eqgUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @PrePersist
    void prePersist() {
        if (eqgiUuid == null) {
            eqgiUuid = UuidV7Generator.generate();
        }
    }
}

