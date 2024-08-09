package com.turkcell.gyt.examService.dataAccess;

import com.turkcell.gyt.examService.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuleRepository extends JpaRepository<Rule, UUID> {
}
