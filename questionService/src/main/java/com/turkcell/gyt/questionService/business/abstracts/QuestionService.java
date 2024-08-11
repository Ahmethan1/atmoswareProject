package com.turkcell.gyt.questionService.business.abstracts;


import com.turkcell.gyt.questionService.business.dtos.question.request.*;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface QuestionService {
    CreatedQuestionRespnose add(CreateQuestionRequest createQuestionRequest, HttpServletRequest request);
    Page<GetAllQuestionResponse> getAll(Pageable pageable);
    UpdatedQuestionResponse update(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest request);
    GetByIdQuestionResponse getById(UUID id);
    void delete(UUID id,HttpServletRequest request);
    GetQuestionByIdResponse getQuestionById(UUID id);
}
