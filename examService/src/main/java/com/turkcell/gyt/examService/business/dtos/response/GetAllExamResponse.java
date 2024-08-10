package com.turkcell.gyt.examService.business.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllExamResponse {
    private UUID id;
    private String examName;
    private double duration;
    private List<String> rules;
}
