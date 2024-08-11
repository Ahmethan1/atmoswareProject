package com.turkcell.gyt.examService.business.rules;

import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.examService.api.client.QuestionClient;
import com.turkcell.gyt.examService.business.messages.ExamMessages;
import com.turkcell.gyt.examService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.examService.dataAccess.ExamRepository;
import com.turkcell.gyt.examService.entity.Exam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExamBusinessRules {
    private ExamRepository examRepository;
    private QuestionClient questionClient;

    public Exam isExistByExamId(UUID id) {
        Optional<Exam> exam = this.examRepository.findById(id);
        if (exam.isEmpty()) {
            throw new BusinessException("Exam Id Not Found");
        }
        return exam.get();
    }

    public void checkRequestRole(String requestRoleName, Exam exam, String userId) {
        if (!requestRoleName.equals("admin")) {
            if (!exam.getUserId().equals(UUID.fromString(userId))) {
                throw new BusinessException(ExamMessages.INVALID_REQUEST_ROLE);
            }
        }

    }

    public void addQuestionsToExam(Exam exam, List<UUID> questionIds) {
        for (UUID questionId : questionIds) {
            GetQuestionAndOption questionAndOption = this.questionClient.getQuestionAndOption(questionId.toString());
            exam.getQuestionAndOptions().add(questionAndOption);
        }

    }
    public Exam checkExamIsStartedAndNotFinished(UUID examId){
        Exam exam =this.examRepository.findById(examId).orElse(null);
        assert exam != null;
        if (exam.getTestStartedDate() == null){
            throw new BusinessException("Exam Not Started Yet");
        }

        if (exam.getTestEndDate() != null && exam.getTestEndDate().isBefore(LocalDateTime.now())){
            throw new BusinessException("Exam Finished");
        }
        return exam;
    }
    public void isQuestionAlready(Exam exam, UUID questionId){
        List<GetQuestionAndOption> getQuestionAndOptions = exam.getQuestionAndOptions();

        boolean isAlreadyAdded = getQuestionAndOptions.stream()
                .anyMatch(questionAndOption -> questionAndOption.getQuestionId().equals(questionId));

        if (isAlreadyAdded){
            throw new BusinessException("This Question Already Added To The Exam ");
        }
    }
}
