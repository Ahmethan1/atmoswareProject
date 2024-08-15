package com.turkcell.gyt.questionService.business.concretes;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.common.Exam.OptionResponse;
import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.rules.QuestionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.QuestionMapper;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExamAndQuestionManagerTest {

    @InjectMocks
    private ExamAndQuestionManager examAndQuestionManager;

    @Mock
    private OptionService optionService;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionBusinessRules questionBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionAndOptionById() {
        // Given
        UUID questionId = UUID.randomUUID();
        QuestionEntity questionEntity = new QuestionEntity();
        List<OptionResponse> optionResponses = List.of(new OptionResponse(), new OptionResponse());
        GetQuestionAndOption getQuestionAndOptionResponse = new GetQuestionAndOption();

        when(questionBusinessRules.isCatalogAlreadyDeleted(questionId)).thenReturn(questionEntity);
        when(optionService.getOptionsByQuestionId(questionId)).thenReturn(optionResponses);
        when(questionMapper.questionToGetQuestionAndOptionResponse(questionEntity)).thenReturn(getQuestionAndOptionResponse);

        doNothing().when(questionBusinessRules).validateAtLeastOneCorrectOption(optionResponses);
        doNothing().when(questionBusinessRules).checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponses);

        GetQuestionAndOption result = examAndQuestionManager.getQuestionAndOptionById(questionId);

        assertThat(result).isNotNull();
        assertThat(result.getOptionResponseList()).isEqualTo(optionResponses);

        verify(questionBusinessRules).isCatalogAlreadyDeleted(questionId);
        verify(optionService).getOptionsByQuestionId(questionId);
        verify(questionBusinessRules).validateAtLeastOneCorrectOption(optionResponses);
        verify(questionBusinessRules).checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponses);
        verify(questionRepository).save(questionEntity);
        verify(questionMapper).questionToGetQuestionAndOptionResponse(questionEntity);
    }
}