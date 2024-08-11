package com.turkcell.gyt.questionService.core.utility.mapper;


import com.turkcell.gyt.common.Exam.OptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.*;
import com.turkcell.gyt.questionService.entity.OptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    //Request to Entity
    @Mapping(source = "questionId",target = "questionEntity.id")
    OptionEntity createOptionRequestToOptionEntity(CreateOptionRequest createOptionRequest);
    @Mapping(source = "questionId",target = "questionEntity.id")
    OptionEntity updatedOptionRequestToOptionEntity(UpdateOptionRequest updateOptionRequest);

    //entityToReponse
    @Mapping(source = "questionEntity.id",target = "questionId")
    CreatedOptionResponse optionEntityToCreatedOptionResponse(OptionEntity optionEntity);
    @Mapping(source = "questionEntity.id",target = "questionId")
    UpdatedOptionResponse optionEntityToUpdatedOptionResponse(OptionEntity optionEntity);

    //@Mapping(source = "imageUrl",target = "imageUrl")
    @Mapping(source = "questionEntity.id",target = "questionId")
    GetAllOptionResponse optionEntityToGetAllOptionResponse(OptionEntity optionEntity);

    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "questionEntity.id",target = "questionId")
    GetByIdOptionResponse optionEntityToGetByIdOptionResponse(OptionEntity optionEntity);

    //OptionResponse optionToOptionResponse(Option option);
    @Mapping(source = "descriptionOption", target = "descriptionOption")
    OptionResponse optionEntityToOptionResponse(OptionEntity optionEntity);

    GetOptionByIdResponse optionEntityToGetOptionByIdResponse(OptionEntity optionEntity);
}
