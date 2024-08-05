package com.turkcell.gyt.managementService.core.utilitiy.mapper;

import com.turkcell.gyt.managementService.core.dtos.request.user.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.request.user.UpdatedResgisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.user.CreatedRegisterResponse;
import com.turkcell.gyt.managementService.core.dtos.response.user.UpdatedRegisterResponse;
import com.turkcell.gyt.managementService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //@Mapping(source = "email", target = "email")
    User registerRequestToUserEntity(RegisterRequest request);
    //@Mapping(source = "email", target = "email")
    CreatedRegisterResponse userToCreatedRegisterResponse(User user);

    User updateRegisterRequestToUser(UpdatedResgisterRequest updatedResgisterRequest);
    UpdatedRegisterResponse userToUpdatedRegisterResponse(User user);
}
