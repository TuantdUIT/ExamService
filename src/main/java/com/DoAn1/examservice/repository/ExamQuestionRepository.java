package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.ExamQuestion;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, UUID> {
    List<ExamQuestion> findByExamUuidOrderByQuestionOrderAsc(UUID examUuid);

    long deleteByExamUuid(UUID examUuid);
}
