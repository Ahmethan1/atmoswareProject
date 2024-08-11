package com.turkcell.gyt.questionService.business.abstracts;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;

import java.util.UUID;

public interface ExamAndQuestionService {
    GetQuestionAndOption getQuestionAndOptionById(UUID questionId);
}
