package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.QuestionMcOption;

public interface QuestionMcOptionRepository extends JpaRepository<QuestionMcOption, UUID> {
    List<QuestionMcOption> findByQuestionUuid(UUID questionUuid);

    List<QuestionMcOption> findByQuestionUuidInOrderByQuestionUuidAscOptionKeyAsc(List<UUID> questionUuids);

    long deleteByQuestionUuid(UUID questionUuid);
}

