package com.turkcell.gyt.questionService.business.dtos.question.response;

import com.turkcell.gyt.questionService.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatedQuestionResponse {
    private UUID id;
    private UUID userId;
    private String userRole;
    private String description;
    private String imageUrl;
    private int optionCount;


}
