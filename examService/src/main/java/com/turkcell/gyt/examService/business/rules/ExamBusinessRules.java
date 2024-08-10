package com.turkcell.gyt.examService.business.rules;

import com.turkcell.gyt.examService.business.messages.ExamMessages;
import com.turkcell.gyt.examService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.examService.dataAccess.ExamRepository;
import com.turkcell.gyt.examService.entity.Exam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExamBusinessRules {
    private ExamRepository examRepository;

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
}
