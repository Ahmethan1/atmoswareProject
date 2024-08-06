package com.turkcell.gyt.questionService.core.utility.mapper;


import com.turkcell.gyt.questionService.business.dtos.option.request.CreateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.request.UpdateOptionRequest;
import com.turkcell.gyt.questionService.business.dtos.option.response.CreatedOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetAllOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.GetByIdOptionResponse;
import com.turkcell.gyt.questionService.business.dtos.option.response.UpdatedOptionResponse;
import com.turkcell.gyt.questionService.entity.OptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    //Request to Entity
    OptionEntity createOptionRequestToOptionEntity(CreateOptionRequest createOptionRequest);
    OptionEntity updatedOptionRequestToOptionEntity(UpdateOptionRequest updateOptionRequest);

    //entityToReponse
    CreatedOptionResponse optionEntityToCreatedOptionResponse(OptionEntity optionEntity);
    UpdatedOptionResponse optionEntityToUpdatedOptionResponse(OptionEntity optionEntity);

    //@Mapping(source = "imageUrl",target = "imageUrl")
    @Mapping(source = "optionEntity.id", target = "questionId")
    @Mapping(source = "id", target = "id")
    GetAllOptionResponse optionEntityToGetAllOptionResponse(OptionEntity optionEntity);

    @Mapping(source = "imageUrl",target = "imageUrl")
    @Mapping(source = "id",target = "id")
    GetByIdOptionResponse optionEntityToGetByIdOptionResponse(OptionEntity optionEntity);


}
