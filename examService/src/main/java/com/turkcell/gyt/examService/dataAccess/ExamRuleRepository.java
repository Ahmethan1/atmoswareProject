package com.turkcell.gyt.examService.dataAccess;

import com.turkcell.gyt.examService.entity.ExamRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRuleRepository extends JpaRepository<ExamRule, UUID> {
}
