package com.turkcell.gyt.questionService.business.dtos.option.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOptionRequest {
    @NotNull
    private UUID id;
    @NotNull(message = "Question ID cannot be null")
    private UUID questionId;

    @Size(max = 500, message = "Option description can be at most 500 characters")
    private String descriptionOption;

    @URL(message = "Image URL must be a valid URL")
    private String imageUrl;

    private boolean isCorrect;
}
