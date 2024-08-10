package com.turkcell.gyt.examService.business.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateExamRequest {
    private String examName;
    private double duration;
}
