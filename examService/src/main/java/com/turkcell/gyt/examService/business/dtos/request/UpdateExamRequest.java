package com.turkcell.gyt.examService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateExamRequest {
    private UUID id;
    private String examName;
    private double duration;
}
