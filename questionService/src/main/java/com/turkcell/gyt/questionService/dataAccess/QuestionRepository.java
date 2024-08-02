package com.turkcell.gyt.questionService.dataAccess;

import com.turkcell.gyt.questionService.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    List<QuestionEntity> findByDeletedDateIsNull();
}
