package com.turkcell.gyt.questionService.business.concretes;


import com.turkcell.gyt.questionService.business.abstracts.QuestionService;
import com.turkcell.gyt.questionService.business.dtos.question.request.CreateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.request.UpdateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.response.CreatedQuestionRespnose;
import com.turkcell.gyt.questionService.business.dtos.question.response.GetAllQuestionResponse;
import com.turkcell.gyt.questionService.business.dtos.question.response.GetByIdQuestionResponse;
import com.turkcell.gyt.questionService.business.dtos.question.response.UpdatedQuestionResponse;
import com.turkcell.gyt.questionService.business.rules.QuestionBusinessRules;
import com.turkcell.gyt.questionService.core.utility.mapper.QuestionMapper;
import com.turkcell.gyt.questionService.dataAccess.QuestionRepository;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionManager implements QuestionService {
    private QuestionRepository questionRepository;
    private QuestionMapper questionMapper;
    private QuestionBusinessRules questionBusinessRules;

    @Override
    @Transactional
    public CreatedQuestionRespnose add(CreateQuestionRequest createQuestionRequest) {
        QuestionEntity questionEntity = this.questionMapper.createQuestionRequestToQuestionEntity(createQuestionRequest);
        QuestionEntity saveQuestion = this.questionRepository.save(questionEntity);

        return this.questionMapper.questionEntityToCreatedQuestionResponse(saveQuestion);
    }

    @Override
    public List<GetAllQuestionResponse> getAll() {
        List<QuestionEntity> questionEntityList = this.questionRepository.findAll();

        return questionEntityList.stream()
                .map(this.questionMapper::questionEntityToGetAllQuestionResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UpdatedQuestionResponse update(UpdateQuestionRequest updateQuestionRequest) {
        this.questionBusinessRules.isQuestionExistById(updateQuestionRequest.getId());

        QuestionEntity questionEntity = this.questionMapper.updateQuestionRequestToQuestionEntity(updateQuestionRequest);
        questionEntity.setUpdatedDate(LocalDateTime.now());

        QuestionEntity savedQuestion = this.questionRepository.save(questionEntity);

        return this.questionMapper.questionEntityToUpdatedQuestionResponse(savedQuestion);
    }

    @Override
    public GetByIdQuestionResponse getById(UUID id) {
        this.questionBusinessRules.isQuestionExistById(id);

        Optional<QuestionEntity> questionEntity = this.questionRepository.findById(id);

        return this.questionMapper.questionEntityToGetByIdQuestionResponse(questionEntity.get());
    }

    @Override
    public void delete(UUID id) {

        this.questionRepository.deleteById(id);

    }
}
