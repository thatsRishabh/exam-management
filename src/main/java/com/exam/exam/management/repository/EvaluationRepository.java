package com.exam.exam.management.repository;

import com.exam.exam.management.entity.exam.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, String> {
    Set<EvaluationEntity> findByUserId(Long userId);
}
