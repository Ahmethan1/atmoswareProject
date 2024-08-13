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
import java.util.*;

@Service
@AllArgsConstructor
public class ExamBusinessRules {
    private ExamRepository examRepository;
    private QuestionClient questionClient;

    public Exam isExistByExamId(UUID id) {
        Optional<Exam> exam = this.examRepository.findById(id);
        if (exam.isEmpty()) {
            throw new BusinessException(ExamMessages.EXAM_NOT_FOUND);
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

    public void checkSameQuestionId(List<UUID> questionIds) {
        Set<UUID> uniqueIds = new HashSet<>();

        for (UUID questionId : questionIds) {
            if (!uniqueIds.add(questionId)) {
                throw new BusinessException("Listede aynı question ID'si var: " + questionId);
            }
        }
    }
    public Exam checkExamIsStartedAndNotFinished(UUID examId) {
        Exam exam = this.examRepository.findById(examId).orElse(null);

        LocalDateTime now = LocalDateTime.now();

        // Testin başlamamış olduğunu kontrol et
//        if (exam.getTestStartedDate() != null && exam.getTestStartedDate().isBefore(now)) {
//            throw new BusinessException(ExamMessages.EXAM_NOT_STARTED_YET);
//        }

        // Testin bitip bitmediğini kontrol et
        if (exam.getTestEndDate() != null && exam.getTestEndDate().isBefore(now)) {
            throw new BusinessException(ExamMessages.EXAM_HAS_FINISHED);
        }

        return exam;
    }

}
