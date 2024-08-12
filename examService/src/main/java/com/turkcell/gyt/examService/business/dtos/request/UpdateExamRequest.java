package com.turkcell.gyt.examService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateExamRequest {
    private UUID id;
    private String examName;
    private double duration;
    private List<String> rules;
    private List<UUID> questionId;
    private LocalDateTime testStartedDate;
}
