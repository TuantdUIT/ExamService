package com.DoAn1.examservice.domain.entity;

import java.time.Instant;
import java.util.UUID;

import com.DoAn1.examservice.util.UuidV7Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "exam_assignment")
public class ExamAssignment {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID assignmentUuid;

    @Column(nullable = false)
    private UUID examUuid;

    @Column(nullable = false)
    private Long gradeId;

    @Column(nullable = false)
    private Instant assignedAt;

    @Column(nullable = false)
    private UUID assignedByUserUuid;

    @PrePersist
    void prePersist() {
        if (assignmentUuid == null) {
            assignmentUuid = UuidV7Generator.generate();
        }
        if (assignedAt == null) {
            assignedAt = Instant.now();
        }
    }
}

