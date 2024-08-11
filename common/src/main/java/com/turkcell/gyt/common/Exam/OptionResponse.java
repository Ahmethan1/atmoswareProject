package com.turkcell.gyt.common.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionResponse {
    private String descriptionOption;
    private boolean isCorrect;
    private String imageUrl;
}
