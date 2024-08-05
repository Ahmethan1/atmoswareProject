package com.turkcell.gyt.questionService.business.dtos.question.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UpdateQuestionRequest {
    @NotNull
    private UUID id;
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @Size(max = 2000, message = "Description can be at most 2000 characters")
    private String description;

    @URL(message = "Image URL must be a valid URL")
    private String imageUrl;

    @Min(value = 2, message = "Option count must be at least 2")
    @Max(value = 5, message = "Option count can be at most 5")
    private int optionCount;
}
