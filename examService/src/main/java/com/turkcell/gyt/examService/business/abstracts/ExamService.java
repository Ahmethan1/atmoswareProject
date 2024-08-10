package com.turkcell.gyt.examService.business.abstracts;

import com.turkcell.gyt.examService.business.dtos.request.CreateExamRequest;
import com.turkcell.gyt.examService.business.dtos.request.UpdateExamRequest;
import com.turkcell.gyt.examService.business.dtos.response.CreatedExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetAllExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetByExamIdResponse;
import com.turkcell.gyt.examService.business.dtos.response.UpdatedExamResponse;
import com.turkcell.gyt.examService.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface ExamService {
    CreatedExamResponse add(CreateExamRequest createExamRequest);
    UpdatedExamResponse update(UpdateExamRequest updateExamRequest);
    Page<GetAllExamResponse> getAll(Pageable pageable);
    GetByExamIdResponse getById(UUID id);
    void delete(UUID id);
}
