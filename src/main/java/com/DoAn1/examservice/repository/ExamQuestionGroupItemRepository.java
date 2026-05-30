package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.DoAn1.examservice.domain.entity.ExamQuestionGroupItem;

public interface ExamQuestionGroupItemRepository extends JpaRepository<ExamQuestionGroupItem, UUID> {
    List<ExamQuestionGroupItem> findByEqgUuid(UUID eqgUuid);

    @Modifying
    @Transactional
    @Query("DELETE FROM ExamQuestionGroupItem e WHERE e.eqgUuid = :eqgUuid")
    long deleteByEqgUuid(@Param("eqgUuid") UUID eqgUuid);
}
