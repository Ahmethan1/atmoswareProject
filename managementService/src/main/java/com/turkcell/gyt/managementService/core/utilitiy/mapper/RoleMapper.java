package com.turkcell.gyt.managementService.core.utilitiy.mapper;

import com.turkcell.gyt.managementService.core.dtos.request.role.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.role.GetByIdRoleResponse;
import com.turkcell.gyt.managementService.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "name", target = "name")
    Role createRoleRequestToRoleEntity(CreateRoleRequest createRoleRequest);
    @Mapping(source = "role.id", target = "id")
    CreatedRoleResponse roleEntityToCreateRoleResponse(Role role);
    @Mapping(source = "role.id", target = "id")
    GetByIdRoleResponse roleEntityToGetByIdRoleResponse(Role role);
}
