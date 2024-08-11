package com.turkcell.gyt.examService.api.client;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.examService.core.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "questionService", url = "http://localhost:10040/questionservice/api/v1/exam-questions", configuration = FeignConfiguration.class)
public interface QuestionClient {
    @GetMapping("/getById/{questionId}")
    GetQuestionAndOption getQuestionAndOption(@PathVariable String questionId);
}
