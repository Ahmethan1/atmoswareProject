package com.turkcell.gyt.examService.entity;

import com.turkcell.gyt.examService.core.baseEntity.BaseEntiy;
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
@Table(name = "exam_rules")
public class ExamRule extends BaseEntiy<UUID> {
    @ManyToOne
    @JoinColumn(name = "exam_id",nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name="rule_id",nullable = false)
    private Rule rule;
}
