package com.turkcell.gyt.managementService.core.utilitiy.mapper;

import com.turkcell.gyt.managementService.core.dtos.request.RegisterRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRegisterResponse;
import com.turkcell.gyt.managementService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructureService.class)
public interface UserMapper {
    User user registerRequestToUserEntity(RegisterRequest request);
    CreatedRegisterResponse userToCreatedRegisterResponse(User user);
}
