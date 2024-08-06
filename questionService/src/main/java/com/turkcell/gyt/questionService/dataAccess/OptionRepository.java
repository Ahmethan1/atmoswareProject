package com.turkcell.gyt.questionService.dataAccess;

import com.turkcell.gyt.questionService.entity.OptionEntity;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OptionRepository extends JpaRepository<OptionEntity, UUID> {
    List<OptionEntity> findByDeletedDateIsNull();
    Page<OptionEntity> findAllByOrderByIdAsc(Pageable pageable);
}
