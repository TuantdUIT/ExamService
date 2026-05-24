package com.DoAn1.examservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.OmrImport;

public interface OmrImportRepository extends JpaRepository<OmrImport, UUID> {
    Optional<OmrImport> findByExternalSubmissionId(String externalSubmissionId);

    boolean existsByExternalSubmissionId(String externalSubmissionId);
}
