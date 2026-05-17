package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.QuestionAnswerKey;

public interface QuestionAnswerKeyRepository extends JpaRepository<QuestionAnswerKey, UUID> {
    Optional<QuestionAnswerKey> findByQuestionUuid(UUID questionUuid);

    List<QuestionAnswerKey> findByQuestionUuidIn(List<UUID> questionUuids);

    long deleteByQuestionUuid(UUID questionUuid);
}

