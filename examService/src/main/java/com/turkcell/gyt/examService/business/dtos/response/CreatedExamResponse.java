package com.turkcell.gyt.examService.business.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreatedExamResponse {
    private UUID id;
    private String examName;
    private double duration;
    private List<String> rules;
}
