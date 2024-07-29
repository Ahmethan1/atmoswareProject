package com.turkcell.gyt.questionService.business.abstracts;


import com.turkcell.gyt.questionService.business.dtos.question.request.*;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;


import java.util.List;
import java.util.UUID;

public interface QuestionService {
    CreatedQuestionRespnose add(CreateQuestionRequest createQuestionRequest);
    List<GetAllQuestionResponse> getAll();
    UpdatedQuestionResponse update(UpdateQuestionRequest updateQuestionRequest);
    GetByIdQuestionResponse getById(UUID id);
    void delete(UUID id);
}
