package com.turkcell.gyt.questionService.business.dtos.option.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOptionByIdResponse {
    private UUID id;
    private UUID questionId;
    private String descriptionOption;
    private boolean isCorrect;
    private String imageUrl;
}
