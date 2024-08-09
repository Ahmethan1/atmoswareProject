package com.turkcell.gyt.examService.entity;

import com.turkcell.gyt.examService.core.baseEntity.BaseEntiy;
import jakarta.persistence.*;
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
@Table(name = "exams")
public class Exam extends BaseEntiy<UUID> {
    private String name;

    @OneToMany(mappedBy = "exam", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ExamRule> ruleEntities;
}
