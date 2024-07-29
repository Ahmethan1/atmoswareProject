package com.turkcell.gyt.questionService.business.dtos.question.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedQuestionRespnose {
    private UUID userId;
    private String description;
    private String imageUrl;
    private int optionCount;
}
