package com.DoAn1.examservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoAn1.examservice.domain.entity.ExamAssignment;

public interface ExamAssignmentRepository extends JpaRepository<ExamAssignment, UUID> {
    List<ExamAssignment> findByGradeId(Long gradeId);
}

