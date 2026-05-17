package com.DoAn1.examservice.domain.entity;

import java.time.Instant;
import java.util.UUID;

import com.DoAn1.examservice.domain.enums.ProctoringEventType;
import com.DoAn1.examservice.util.UuidV7Generator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_proctoring_event", indexes = {
        @Index(name = "idx_exam_proctoring_event_attempt_time", columnList = "attemptUuid,eventTime")
})
public class ExamProctoringEvent {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID eventUuid;

    @Column(nullable = false)
    private UUID attemptUuid;

    @Column(nullable = false)
    private Instant eventTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ProctoringEventType eventType;

    @Column(columnDefinition = "TEXT")
    private String eventPayload;

    @PrePersist
    void prePersist() {
        if (eventUuid == null) {
            eventUuid = UuidV7Generator.generate();
        }
        if (eventTime == null) {
            eventTime = Instant.now();
        }
    }
}

