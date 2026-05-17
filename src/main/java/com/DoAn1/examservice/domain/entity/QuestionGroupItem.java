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
@Table(name = "question_group_item", uniqueConstraints = {
        @UniqueConstraint(name = "uk_qg_item_group_question", columnNames = { "questionGroupUuid", "questionUuid" })
}, indexes = {
        @Index(name = "idx_qg_item_group", columnList = "questionGroupUuid")
})
public class QuestionGroupItem {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID questionGroupItemUuid;

    @Column(nullable = false)
    private UUID questionGroupUuid;

    @Column(nullable = false)
    private UUID questionUuid;

    @PrePersist
    void prePersist() {
        if (questionGroupItemUuid == null) {
            questionGroupItemUuid = UuidV7Generator.generate();
        }
    }
}
