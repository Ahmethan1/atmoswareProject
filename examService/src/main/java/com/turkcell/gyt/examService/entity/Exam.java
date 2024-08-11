package com.turkcell.gyt.examService.entity;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "exams")
public class Exam {
    @Id
    private UUID id;

    private String examName;
    private UUID userId;
    private String userRole;
    private double duration;

    private LocalDateTime testStartedDate;
    private LocalDateTime testEndDate;

    @Field("rules")
    private List<String> rules;

    @Field("questionAndOptions")
    private List<GetQuestionAndOption> questionAndOptions = new ArrayList<>();

    @CreatedDate
    @Field("createdDate")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("updatedDate")
    private LocalDateTime updatedDate;

    @Field("deletedDate")

    private LocalDateTime deletedDate;

    public Exam() {
        this.id = UUID.randomUUID();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }


}
