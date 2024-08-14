package com.turkcell.gyt.questionService.business.concretes;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.common.Exam.OptionResponse;
import com.turkcell.gyt.questionService.business.abstracts.ExamAndQuestionService;
import com.turkcell.gyt.questionService.business.abstracts.OptionService;
import com.turkcell.gyt.questionService.business.rules.QuestionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.QuestionMapper;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import com.turkcell.gyt.questionService.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamAndQuestionManager implements ExamAndQuestionService {
    private final OptionService optionService;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final QuestionBusinessRules questionBusinessRules;

    @Override
    public GetQuestionAndOption getQuestionAndOptionById(UUID questionId) {
        QuestionEntity questionEntity = this.questionBusinessRules.isCatalogAlreadyDeleted(questionId);

        List<OptionResponse> optionResponses  = this.optionService.getOptionsByQuestionId(questionId);

        this.questionBusinessRules.validateAtLeastOneCorrectOption(optionResponses);
        this.questionBusinessRules.checkOptionCountIsLowerThanFiveAngHigherThanTwo(optionResponses);

        assert questionEntity != null;
        questionEntity.setStatus(Status.UNAVAILABLE);
        this.questionRepository.save(questionEntity);

        GetQuestionAndOption getQuestionAndOption = this.questionMapper.questionToGetQuestionAndOptionResponse(questionEntity);
        getQuestionAndOption.setOptionResponseList(optionResponses);

        return getQuestionAndOption;
    }
}
