package com.turkcell.gyt.examService.business.concretes;

import com.turkcell.gyt.examService.business.abstracts.ExamService;
import com.turkcell.gyt.examService.business.dtos.request.CreateExamRequest;
import com.turkcell.gyt.examService.business.dtos.request.UpdateExamRequest;
import com.turkcell.gyt.examService.business.dtos.response.CreatedExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetAllExamResponse;
import com.turkcell.gyt.examService.business.dtos.response.GetByExamIdResponse;
import com.turkcell.gyt.examService.business.dtos.response.UpdatedExamResponse;
import com.turkcell.gyt.examService.business.rules.ExamBusinessRules;
import com.turkcell.gyt.examService.core.utilitiy.mapping.ModelMapperService;
import com.turkcell.gyt.examService.dataAccess.ExamRepository;
import com.turkcell.gyt.examService.entity.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamManager implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapperService examMapper;
    private final ExamBusinessRules examBusinessRules;

    @Override
    public CreatedExamResponse add(CreateExamRequest createExamRequest) {
        Exam exam = this.examMapper.forRequest().map(createExamRequest, Exam.class);

        Exam savedExam = this.examRepository.save(exam);
        return this.examMapper.forResponse().map(savedExam, CreatedExamResponse.class);
    }

    @Override
    public UpdatedExamResponse update(UpdateExamRequest updateExamRequest) {
        this.examBusinessRules.isExistByExamId(updateExamRequest.getId());

        Exam exam = this.examMapper.forRequest().map(updateExamRequest,Exam.class);

        Exam updatedExam =this.examRepository.save(exam);

        return this.examMapper.forResponse().map(updatedExam, UpdatedExamResponse.class);
    }

    @Override
    public Page<GetAllExamResponse> getAll(Pageable pageable) {
        Page<Exam> exams = this.examRepository.findAllByOrderByIdAsc(pageable);
        return exams.map(exam -> this.examMapper.forResponse().map(exam,GetAllExamResponse.class));
    }

    @Override
    public GetByExamIdResponse getById(UUID id) {
        Exam exam =this.examBusinessRules.isExistByExamId(id);

        return this.examMapper.forResponse().map(exam, GetByExamIdResponse.class);
    }

    @Override
    public void delete(UUID id) {
        Exam exam = this.examBusinessRules.isExistByExamId(id);
        this.examRepository.deleteById(exam.getId());

    }
}
