package com.turkcell.gyt.questionService.business.rules;

import com.turkcell.gyt.common.Exam.OptionResponse;
import com.turkcell.gyt.questionService.business.messages.QuestionMessages;
import com.turkcell.gyt.questionService.core.business.abstracts.MessageService;
import com.turkcell.gyt.questionService.core.utility.exceptions.types.BusinessException;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionBusinessRules {
    private QuestionRepository questionRepository;
    private MessageService messageService;

    public QuestionEntity isQuestionExistById(UUID id) {
        Optional<QuestionEntity> questionEntity = this.questionRepository.findById(id);

        if (questionEntity.isEmpty()) {
            throw new BusinessException(messageService.getMessage(QuestionMessages.QUESTION_ID_NOT_FOUND));
        }

        return questionEntity.get();
    }

    public QuestionEntity isCatalogAlreadyDeleted(UUID id) {

        QuestionEntity questionEntity = this.isQuestionExistById(id);

        if (questionEntity.getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(QuestionMessages.QUESTION_ALREADY_DELETED));
        }

        return questionEntity;
    }

    public void checkRequestRole(String requestRoleName, QuestionEntity question, String userId) {
        if (!requestRoleName.equals("admin")) {
            if (!question.getUserId().equals(UUID.fromString(userId))) {
                throw new BusinessException(QuestionMessages.INVALID_REQUEST_ROLE);
            }
        }
    }

    public void checkOptionCountIsLowerThanFiveAngHigherThanTwo(List<OptionResponse> optionResponseList) {
        if (optionResponseList.size() > 5) {
            throw new BusinessException(QuestionMessages.OPTION_COUNT_EXCEEDS_LIMIT);
        } else if (optionResponseList.size() < 2) {
            throw new BusinessException(QuestionMessages.OPTION_COUNT_BELOW_MINIMUM);
        }
    }

    public void validateAtLeastOneCorrectOption(List<OptionResponse> optionResponseList) {
        boolean correctOption = false;

        for (OptionResponse optionResponse : optionResponseList) {
            if (optionResponse.isCorrect()) {
                correctOption = true;
                break;
            }
        }
        if (!correctOption) {
            throw new BusinessException(QuestionMessages.AT_LEAST_ONE_CORRECT_OPTION_REQUIRED);
        }
    }


}
