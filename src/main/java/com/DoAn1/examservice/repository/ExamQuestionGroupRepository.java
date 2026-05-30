package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.DoAn1.examservice.domain.entity.ExamQuestionGroup;

public interface ExamQuestionGroupRepository extends JpaRepository<ExamQuestionGroup, UUID> {
    List<ExamQuestionGroup> findByExamUuidOrderByDisplayOrderAsc(UUID examUuid);

    @Modifying
    @Transactional
    @Query("DELETE FROM ExamQuestionGroup e WHERE e.examUuid = :examUuid")
    long deleteByExamUuid(@Param("examUuid") UUID examUuid);
}
