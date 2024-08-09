package com.turkcell.gyt.examService.entity;

import com.turkcell.gyt.examService.core.baseEntity.BaseEntiy;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "rules")
public class Rule extends BaseEntiy<UUID> {
    private String description;

    @OneToMany(mappedBy = "rule",cascade = CascadeType.ALL)
    private List<ExamRule> examRules;
}
