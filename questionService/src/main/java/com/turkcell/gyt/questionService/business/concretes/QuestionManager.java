package com.turkcell.gyt.questionService.business.concretes;


import com.atmosware.core.utils.JwtService;
import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.abstracts.QuestionService;
import com.turkcell.gyt.questionService.business.dtos.question.request.CreateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.request.UpdateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;
import com.turkcell.gyt.questionService.business.rules.QuestionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.QuestionMapper;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import com.turkcell.gyt.questionService.entity.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionManager implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuestionBusinessRules questionBusinessRules;
    private final JwtService jwtService;
    private final OptionService optionService;

    @Override
    @Transactional
    public CreatedQuestionRespnose add(CreateQuestionRequest createQuestionRequest, HttpServletRequest request) {

        String token = extractJwtFromRequest(request);
        String role = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        QuestionEntity questionEntity = this.questionMapper.createQuestionRequestToQuestionEntity(createQuestionRequest);
        questionEntity.setUserRole(role);
        questionEntity.setStatus(Status.AVAILABLE);
        questionEntity.setUserId(UUID.fromString(userId));

        QuestionEntity saveQuestion = this.questionRepository.save(questionEntity);

        return this.questionMapper.questionEntityToCreatedQuestionResponse(saveQuestion);
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public Page<GetAllQuestionResponse> getAll(Pageable pageable) {
        Page<QuestionEntity> questionEntities = this.questionRepository.findAllByOrderByIdAsc(pageable);

        return questionEntities.map(this.questionMapper::questionEntityToGetAllQuestionResponse);
    }

    @Override
    @Transactional
    public UpdatedQuestionResponse update(UpdateQuestionRequest updateQuestionRequest, HttpServletRequest request) {
        this.questionBusinessRules.isQuestionExistById(updateQuestionRequest.getId());
        QuestionEntity question = this.questionMapper.updateQuestionRequestToQuestionEntity(updateQuestionRequest);

        //QuestionEntity question = this.questionRepository.findById(updateQuestionRequest.getId()).orElse(null);

        String token = extractJwtFromRequest(request);
        String role = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        this.questionBusinessRules.checkRequestRole(role, question, userId);

        //QuestionEntity questionEntity = this.questionMapper.updateQuestionRequestToQuestionEntity(updateQuestionRequest);

        question.setUpdatedDate(LocalDateTime.now());
        question.setStatus(Status.AVAILABLE);

        question.setUserRole(role);
        this.questionRepository.save(question);
        //QuestionEntity savedQuestion = this.questionMapper.updateQuestionRequestToQuestionEntity(updateQuestionRequest);

        return this.questionMapper.questionEntityToUpdatedQuestionResponse(question);
    }

    @Override
    public GetByIdQuestionResponse getById(UUID id) {
        this.questionBusinessRules.isCatalogAlreadyDeleted(id);

        Optional<QuestionEntity> questionEntity = this.questionRepository.findById(id);

        return this.questionMapper.questionEntityToGetByIdQuestionResponse(questionEntity.get());
    }

    @Override
    public void delete(UUID id, HttpServletRequest request) {
        QuestionEntity questionEntity = this.questionBusinessRules.isCatalogAlreadyDeleted(id);

        String token = extractJwtFromRequest(request);
        String roleName = this.jwtService.extractRoles(token);
        String userId = this.jwtService.extractUserId(token);

        assert questionEntity != null;
        this.questionBusinessRules.checkRequestRole(roleName, questionEntity, userId);

        questionEntity.setDeletedDate(LocalDateTime.now());

        this.questionRepository.save(questionEntity);

        this.optionService.delete(id);

    }

    @Override
    public GetQuestionByIdResponse getQuestionById(UUID id) {

        QuestionEntity question = this.questionBusinessRules.isQuestionExistById(id);

        return this.questionMapper.questionEntityToGetQuestionByIdResponse(question);
    }
}
