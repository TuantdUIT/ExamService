package com.DoAn1.examservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.ExamPaper;

public interface ExamPaperRepository extends JpaRepository<ExamPaper, UUID> {
    Optional<ExamPaper> findByExamUuidAndPaperCode(UUID examUuid, String paperCode);

    boolean existsByExamUuidAndPaperCode(UUID examUuid, String paperCode);
}
