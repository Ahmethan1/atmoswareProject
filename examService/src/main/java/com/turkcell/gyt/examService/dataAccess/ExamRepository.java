package com.turkcell.gyt.examService.dataAccess;

import com.turkcell.gyt.examService.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {
}
