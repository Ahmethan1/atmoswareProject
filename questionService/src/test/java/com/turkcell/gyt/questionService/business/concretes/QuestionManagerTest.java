package com.turkcell.gyt.questionService.business.concretes;


import com.atmosware.core.utils.JwtService;
import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.dtos.question.request.CreateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.request.UpdateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;
import com.turkcell.gyt.questionService.business.rules.QuestionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.QuestionMapper;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuestionManagerTest {

    @InjectMocks
    private QuestionManager questionManager;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private QuestionBusinessRules questionBusinessRules;

    @Mock
    private JwtService jwtService;

    @Mock
    private OptionService optionService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {

        String token = "testToken";
        String role = "USER";
        String userId = UUID.randomUUID().toString();

        CreateQuestionRequest createQuestionRequest = new CreateQuestionRequest();
        QuestionEntity questionEntity = new QuestionEntity();
        CreatedQuestionRespnose createdQuestionResponse = new CreatedQuestionRespnose();

        when(jwtService.extractRoles(token)).thenReturn(role);
        when(jwtService.extractUserId(token)).thenReturn(userId);
        when(questionMapper.createQuestionRequestToQuestionEntity(createQuestionRequest)).thenReturn(questionEntity);
        when(questionRepository.save(any(QuestionEntity.class))).thenReturn(questionEntity);
        when(questionMapper.questionEntityToCreatedQuestionResponse(questionEntity)).thenReturn(createdQuestionResponse);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        CreatedQuestionRespnose response = questionManager.add(createQuestionRequest, request);

        assertEquals(createdQuestionResponse, response);
        verify(questionMapper).createQuestionRequestToQuestionEntity(createQuestionRequest);
        verify(questionRepository).save(questionEntity);
        verify(questionMapper).questionEntityToCreatedQuestionResponse(questionEntity);
    }


    @Test
    void testExtractJwtFromRequest() {

        String token = "testToken";
        String bearerToken = "Bearer " + token;

        when(request.getHeader("Authorization")).thenReturn(bearerToken);

        String extractedToken = questionManager.extractJwtFromRequest(request);

        assertEquals(token, extractedToken);
    }

    @Test
    void testExtractJwtFromRequest_NoAuthorizationHeader() {
        when(request.getHeader("Authorization")).thenReturn(null);

        String extractedToken = questionManager.extractJwtFromRequest(request);

        assertEquals(null, extractedToken);
    }

    @Test
    void testExtractJwtFromRequest_InvalidHeaderFormat() {

        String invalidHeader = "InvalidHeader";

        when(request.getHeader("Authorization")).thenReturn(invalidHeader);

        String extractedToken = questionManager.extractJwtFromRequest(request);

        assertEquals(null, extractedToken);
    }
    @Test
    void testGetAll() {

        Pageable pageable = Pageable.unpaged();
        QuestionEntity questionEntity = new QuestionEntity();
        GetAllQuestionResponse questionResponse = new GetAllQuestionResponse();

        List<QuestionEntity> questionEntities = Collections.singletonList(questionEntity);
        Page<QuestionEntity> page = new PageImpl<>(questionEntities, pageable, questionEntities.size());

        when(questionRepository.findAllByOrderByIdAsc(pageable)).thenReturn(page);
        when(questionMapper.questionEntityToGetAllQuestionResponse(questionEntity)).thenReturn(questionResponse);

        Page<GetAllQuestionResponse> response = questionManager.getAll(pageable);

        assertEquals(1, response.getTotalElements());
        assertEquals(questionResponse, response.getContent().get(0));
        verify(questionRepository).findAllByOrderByIdAsc(pageable);
        verify(questionMapper).questionEntityToGetAllQuestionResponse(questionEntity);
    }
    @Test
    void testUpdate() {

        UUID questionId = UUID.randomUUID();
        String token = "testToken";
        String role = "USER";
        String userId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        UpdateQuestionRequest updateQuestionRequest = new UpdateQuestionRequest();
        updateQuestionRequest.setId(questionId);

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setUpdatedDate(now);
        UpdatedQuestionResponse updatedQuestionResponse = new UpdatedQuestionResponse();

        when(questionBusinessRules.isQuestionExistById(questionId)).thenReturn(questionEntity);
        when(questionMapper.updateQuestionRequestToQuestionEntity(updateQuestionRequest)).thenReturn(questionEntity);
        when(jwtService.extractRoles(token)).thenReturn(role);
        when(jwtService.extractUserId(token)).thenReturn(userId);
        when(questionMapper.questionEntityToUpdatedQuestionResponse(questionEntity)).thenReturn(updatedQuestionResponse);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(questionRepository.save(any(QuestionEntity.class))).thenReturn(questionEntity);

        UpdatedQuestionResponse response = questionManager.update(updateQuestionRequest, request);

        assertEquals(updatedQuestionResponse, response);

        assertEquals(now.withNano(0), questionEntity.getUpdatedDate().withNano(0));
        verify(questionBusinessRules).isQuestionExistById(questionId);
        verify(questionMapper).updateQuestionRequestToQuestionEntity(updateQuestionRequest);
        verify(jwtService).extractRoles(token);
        verify(jwtService).extractUserId(token);
        verify(questionRepository).save(questionEntity);
        verify(questionMapper).questionEntityToUpdatedQuestionResponse(questionEntity);
    }
    @Test
    void testGetById() {

        UUID questionId = UUID.randomUUID();
        QuestionEntity questionEntity = new QuestionEntity();
        GetByIdQuestionResponse getByIdQuestionResponse = new GetByIdQuestionResponse();

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(questionEntity));
        when(questionMapper.questionEntityToGetByIdQuestionResponse(questionEntity)).thenReturn(getByIdQuestionResponse);

        GetByIdQuestionResponse response = questionManager.getById(questionId);

        assertEquals(getByIdQuestionResponse, response);

        verify(questionBusinessRules).isCatalogAlreadyDeleted(questionId);
        verify(questionRepository).findById(questionId);
        verify(questionMapper).questionEntityToGetByIdQuestionResponse(questionEntity);
    }

    @Test
    void testGetById_NotFound() {

        UUID questionId = UUID.randomUUID();

        when(questionRepository.findById(questionId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> questionManager.getById(questionId));

        verify(questionBusinessRules).isCatalogAlreadyDeleted(questionId);
        verify(questionRepository).findById(questionId);
    }
    @Test
    void testDelete() {

        UUID questionId = UUID.randomUUID();
        String token = "testToken";
        String role = "USER";
        String userId = UUID.randomUUID().toString();

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionId);

        when(questionBusinessRules.isCatalogAlreadyDeleted(questionId)).thenReturn(questionEntity);
        when(jwtService.extractRoles(token)).thenReturn(role);
        when(jwtService.extractUserId(token)).thenReturn(userId);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        doNothing().when(optionService).delete(questionId);

        questionManager.delete(questionId, request);

        assertNotNull(questionEntity.getDeletedDate());
        verify(questionBusinessRules).isCatalogAlreadyDeleted(questionId);
        verify(questionBusinessRules).checkRequestRole(role, questionEntity, userId);
        verify(jwtService).extractRoles(token);
        verify(jwtService).extractUserId(token);
        verify(questionRepository).save(questionEntity);
        verify(optionService).delete(questionId);
    }
    @Test
    void testGetQuestionById() {

        UUID questionId = UUID.randomUUID();

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionId);

        GetQuestionByIdResponse getQuestionByIdResponse = new GetQuestionByIdResponse();

        when(questionBusinessRules.isQuestionExistById(questionId)).thenReturn(questionEntity);
        when(questionMapper.questionEntityToGetQuestionByIdResponse(questionEntity)).thenReturn(getQuestionByIdResponse);

        GetQuestionByIdResponse response = questionManager.getQuestionById(questionId);

        assertEquals(getQuestionByIdResponse, response);
        verify(questionBusinessRules).isQuestionExistById(questionId);
        verify(questionMapper).questionEntityToGetQuestionByIdResponse(questionEntity);
    }

    @Test
    void testGetQuestionById_NotFound() {
        UUID questionId = UUID.randomUUID();

        when(questionBusinessRules.isQuestionExistById(questionId)).thenThrow(new RuntimeException("Question not found"));

        assertThrows(RuntimeException.class, () -> questionManager.getQuestionById(questionId));

        verify(questionBusinessRules).isQuestionExistById(questionId);
        verify(questionMapper, never()).questionEntityToGetQuestionByIdResponse(any());
    }

}