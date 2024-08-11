package com.turkcell.gyt.questionService.entity;

import com.turkcell.gyt.questionService.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "options")
public class OptionEntity extends BaseEntity<UUID> {

    private String descriptionOption;
    private String imageUrl;
    private boolean isCorrect;

    @ManyToOne()
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

}
