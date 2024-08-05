package com.turkcell.gyt.questionService.business.rules;

import com.turkcell.gyt.questionService.business.messages.QuestionMessages;
import com.turkcell.gyt.questionService.core.business.abstracts.MessageService;
import com.turkcell.gyt.questionService.core.utility.exceptions.types.BusinessException;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;

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


}
