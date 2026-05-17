package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.QuestionTrueFalseStatement;

public interface QuestionTrueFalseStatementRepository extends JpaRepository<QuestionTrueFalseStatement, UUID> {
    List<QuestionTrueFalseStatement> findByQuestionUuidOrderByStatementOrderAsc(UUID questionUuid);

    List<QuestionTrueFalseStatement> findByQuestionUuidInOrderByQuestionUuidAscStatementOrderAsc(List<UUID> questionUuids);

    long deleteByQuestionUuid(UUID questionUuid);
}

