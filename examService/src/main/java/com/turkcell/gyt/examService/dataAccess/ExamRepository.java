package com.turkcell.gyt.examService.dataAccess;

import com.turkcell.gyt.examService.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ExamRepository extends MongoRepository<Exam, UUID> {
    Page<Exam> findAllByOrderByIdAsc(Pageable pageable);
}
