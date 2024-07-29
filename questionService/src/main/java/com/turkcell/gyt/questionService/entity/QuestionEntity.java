package com.turkcell.gyt.questionService.entity;

import com.turkcell.gyt.questionService.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity<UUID> {
    private UUID userId;
    private String description;
    private int optionCount;
    private String imageUrl;
    @OneToMany(mappedBy = "questionEntity")
    private List<OptionEntity> options;
}
