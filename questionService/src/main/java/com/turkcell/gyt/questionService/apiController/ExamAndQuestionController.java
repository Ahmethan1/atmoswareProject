package com.turkcell.gyt.questionService.apiController;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.questionService.business.abstracts.ExamAndQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/questionservice/api/v1/exam-questions")
public class ExamAndQuestionController {
    private ExamAndQuestionService examAndQuestionService;
    @GetMapping("/getQuestionAndOption/{questionId}")
    public GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId){
        return this.examAndQuestionService.getQuestionAndOptionById(UUID.fromString(questionId));
    }
}
