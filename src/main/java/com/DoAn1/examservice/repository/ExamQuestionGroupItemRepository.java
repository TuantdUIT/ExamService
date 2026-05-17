package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.ExamQuestionGroupItem;

public interface ExamQuestionGroupItemRepository extends JpaRepository<ExamQuestionGroupItem, UUID> {
    List<ExamQuestionGroupItem> findByEqgUuid(UUID eqgUuid);

    long deleteByEqgUuid(UUID eqgUuid);
}
