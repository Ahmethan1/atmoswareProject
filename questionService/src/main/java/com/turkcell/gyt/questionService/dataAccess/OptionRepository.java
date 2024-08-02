package com.turkcell.gyt.questionService.dataAccess;

import com.turkcell.gyt.questionService.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OptionRepository extends JpaRepository<OptionEntity, UUID> {
    List<OptionEntity> findByDeletedDateIsNull();
}
