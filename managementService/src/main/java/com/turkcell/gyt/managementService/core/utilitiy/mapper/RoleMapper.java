package com.turkcell.gyt.managementService.core.utilitiy.mapper;

import com.turkcell.gyt.managementService.core.dtos.request.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.GetByIdRoleResponse;
import com.turkcell.gyt.managementService.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructureService.class)
public interface RoleMapper {
    @Mapping(source = "name", target = "name")
    Role createRoleRequestToRoleEntity(CreateRoleRequest createRoleRequest);
    @Mapping(source = "name", target = "name")
    CreatedRoleResponse roleEntityToCreateRoleResponse(Role role);

    GetByIdRoleResponse roleEntityToGetByIdRoleResponse(Role role);
}
