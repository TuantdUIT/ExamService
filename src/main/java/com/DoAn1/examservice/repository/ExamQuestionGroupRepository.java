package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.ExamQuestionGroup;

public interface ExamQuestionGroupRepository extends JpaRepository<ExamQuestionGroup, UUID> {
    List<ExamQuestionGroup> findByExamUuidOrderByDisplayOrderAsc(UUID examUuid);

    long deleteByExamUuid(UUID examUuid);
}
