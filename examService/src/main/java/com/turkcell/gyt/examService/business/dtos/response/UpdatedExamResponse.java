package com.turkcell.gyt.examService.business.dtos.response;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedExamResponse {
    private UUID id;
    private String examName;
    private double duration;
    private List<String> rules;
    private List<GetQuestionAndOption> getQuestionAndOptions;
    private LocalDateTime testStartedDate;
    private LocalDateTime testEndDate;
}
