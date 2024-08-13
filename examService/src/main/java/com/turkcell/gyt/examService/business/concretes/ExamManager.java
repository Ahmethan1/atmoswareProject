package com.turkcell.gyt.examService.business.concretes;

import com.atmosware.core.utils.JwtService;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamManager implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapperService modelMapperService;
    private final ExamBusinessRules examBusinessRules;
    private final JwtService jwtService;
    //private final QuestionClient questionClient;

    @Override
    public CreatedExamResponse add(CreateExamRequest createExamRequest, HttpServletRequest request) {

        String token = extractJwtFromRequest(request);
        String role = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        Exam exam = this.modelMapperService.forRequest().map(createExamRequest, Exam.class);
        exam.setUserRole(role);
        exam.setUserId(UUID.fromString(userId));

        LocalDateTime testStartedDate = createExamRequest.getTestStartedDate();
        LocalDateTime testEndDate = testStartedDate.plusMinutes((long) exam.getDuration());

        exam.setTestStartedDate(testStartedDate);
        exam.setTestEndDate(testEndDate);


        this.examBusinessRules.checkSameQuestionId(createExamRequest.getQuestionId());
        this.examBusinessRules.addQuestionsToExam(exam, createExamRequest.getQuestionId());


        Exam savedExam = this.examRepository.save(exam);
        return this.modelMapperService.forResponse().map(savedExam, CreatedExamResponse.class);
    }

    @Override
    @Transactional
    public UpdatedExamResponse update(UpdateExamRequest updateExamRequest, HttpServletRequest request) {

        this.examBusinessRules.isExistByExamId(updateExamRequest.getId());
        this.examBusinessRules.checkExamIsStartedAndNotFinished(updateExamRequest.getId());

        Exam exam = this.modelMapperService.forRequest().map(updateExamRequest, Exam.class);
        //Exam exam =this.examRepository.findById(updateExamRequest.getId()).orElse(null);

        String token = extractJwtFromRequest(request);
        String role = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        LocalDateTime testStartedDate = updateExamRequest.getTestStartedDate();
        LocalDateTime testEndDate = testStartedDate.plusMinutes((long) exam.getDuration());

        exam.setTestStartedDate(testStartedDate);
        exam.setTestEndDate(testEndDate);

        this.examBusinessRules.checkRequestRole(role, exam, userId);
        this.examBusinessRules.addQuestionsToExam(exam, updateExamRequest.getQuestionId());
        this.examBusinessRules.checkSameQuestionId(updateExamRequest.getQuestionId());


        exam.setUserRole(role);


        return this.modelMapperService.forResponse().map(this.examRepository.save(exam), UpdatedExamResponse.class);
    }

    @Override
    public Page<GetAllExamResponse> getAll(Pageable pageable) {
        Page<Exam> exams = this.examRepository.findAllByOrderByIdAsc(pageable);
        return exams.map(exam -> this.modelMapperService.forResponse().map(exam, GetAllExamResponse.class));
    }

    @Override
    public GetByExamIdResponse getById(UUID id) {
        Exam exam = this.examBusinessRules.isExistByExamId(id);

        return this.modelMapperService.forResponse().map(exam, GetByExamIdResponse.class);
    }

    @Override
    public void delete(UUID id, HttpServletRequest request) {
        Exam exam = this.examBusinessRules.isExistByExamId(id);

        String token = extractJwtFromRequest(request);
        String role = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.examBusinessRules.checkRequestRole(role, exam, userId);

        this.examRepository.deleteById(exam.getId());

    }

    public String extractJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
