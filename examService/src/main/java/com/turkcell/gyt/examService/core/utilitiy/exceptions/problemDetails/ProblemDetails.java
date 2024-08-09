package com.turkcell.gyt.examService.core.utilitiy.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProblemDetails {
    //rfce standarts
    private String title;
    private String detail;
    private String Status;
    private String Type;
}
