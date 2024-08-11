package com.turkcell.gyt.examService.business.dtos.request;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateExamRequest {
    private String examName;
    private double duration;
    private List<String> rules;
    private List<UUID> questionId;
}
