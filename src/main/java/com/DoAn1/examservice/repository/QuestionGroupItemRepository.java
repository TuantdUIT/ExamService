package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.QuestionGroupItem;

public interface QuestionGroupItemRepository extends JpaRepository<QuestionGroupItem, UUID> {
    List<QuestionGroupItem> findByQuestionGroupUuid(UUID questionGroupUuid);

    long deleteByQuestionGroupUuid(UUID questionGroupUuid);
}
