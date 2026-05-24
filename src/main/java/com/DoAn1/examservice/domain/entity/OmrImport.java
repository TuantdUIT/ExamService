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
@Table(name = "omr_import", uniqueConstraints = {
        @UniqueConstraint(name = "uk_omr_import_external_submission", columnNames = { "externalSubmissionId" })
}, indexes = {
        @Index(name = "idx_omr_import_exam_student", columnList = "examUuid,studentUuid"),
        @Index(name = "idx_omr_import_attempt", columnList = "attemptUuid")
})
public class OmrImport {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID omrImportUuid;

    @Column(nullable = false)
    private UUID examUuid;

    @Column(nullable = false)
    private UUID paperUuid;

    @Column(nullable = false, length = 50)
    private String paperCode;

    @Column(nullable = false)
    private UUID studentUuid;

    @Column(nullable = false)
    private UUID attemptUuid;

    @Column(length = 100)
    private String externalSubmissionId;

    private Instant scannedAt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String rawPayloadJson;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, updatable = false)
    private Instant importedAt;

    @PrePersist
    void prePersist() {
        if (omrImportUuid == null) {
            omrImportUuid = UuidV7Generator.generate();
        }
        if (importedAt == null) {
            importedAt = Instant.now();
        }
    }
}
