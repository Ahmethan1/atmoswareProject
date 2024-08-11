package com.turkcell.gyt.examService.api.client;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "questionService", url = "http://localhost:10040/questionservice/api/v1/exam-questions")
public interface QuestionClient {
    @GetMapping("/getById/{id}")
    GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId);
}
