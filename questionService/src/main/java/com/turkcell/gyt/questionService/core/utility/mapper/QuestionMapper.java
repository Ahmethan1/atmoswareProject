package com.turkcell.gyt.questionService.core.utility.mapper;


import com.turkcell.gyt.common.Exam.GetQuestionAndOption;
import com.turkcell.gyt.questionService.business.dtos.question.request.CreateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.request.UpdateQuestionRequest;
import com.turkcell.gyt.questionService.business.dtos.question.response.*;
import com.turkcell.gyt.questionService.entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    //Request to Entity
    @Mapping(source = "imageUrl", target = "imageUrl")
    QuestionEntity createQuestionRequestToQuestionEntity(CreateQuestionRequest createQuestionRequest);
    @Mapping(source = "imageUrl", target = "imageUrl")
    //@Mapping(target = "status", ignore = true)
    QuestionEntity updateQuestionRequestToQuestionEntity(UpdateQuestionRequest updateQuestionRequest);



    //entityToReponse
    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "id",target = "id")
    CreatedQuestionRespnose questionEntityToCreatedQuestionResponse(QuestionEntity questionEntity);
    @Mapping(source = "imageUrl", target = "imageUrl")
    UpdatedQuestionResponse questionEntityToUpdatedQuestionResponse(QuestionEntity questionEntity);


    GetAllQuestionResponse questionEntityToGetAllQuestionResponse(QuestionEntity questionEntity);
    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "id",target = "id")
    GetByIdQuestionResponse questionEntityToGetByIdQuestionResponse(QuestionEntity questionEntity);
    @Mapping(source = "id",target = "questionId")
    GetQuestionAndOption questionToGetQuestionAndOptionResponse(QuestionEntity questionEntity);
    GetQuestionByIdResponse questionEntityToGetQuestionByIdResponse(QuestionEntity questionEntity);
}
