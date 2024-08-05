package com.turkcell.gyt.questionService.business.abstracts;


import com.turkcell.gyt.questionService.business.dtos.question.request.*;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;
import java.util.UUID;

public interface QuestionService {
    CreatedQuestionRespnose add(CreateQuestionRequest createQuestionRequest, HttpServletRequest request);
    List<GetAllQuestionResponse> getAll();
    UpdatedQuestionResponse update(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest request);
    GetByIdQuestionResponse getById(UUID id);
    void delete(UUID id,HttpServletRequest request);
}
