package com.turkcell.gyt.managementService.core.utilitiy.mapper;

import com.turkcell.gyt.managementService.core.dtos.request.userRole.CreateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.UpdateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedUserRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.userRole.UpdatedUserRoleResponse;
import com.turkcell.gyt.managementService.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "role.id",source = "roleId")
    UserRole createUserRoleRequestToUserRoleEntity(CreateUserRoleRequest createUserRoleRequest);
    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "role.id",source = "roleId")
    UserRole updatedUserRoleRequestToUserRoleEntity(UpdateUserRoleRequest updateUserRoleRequest);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "roleId",source ="role.id" )
    CreatedUserRoleResponse userRoleToCreatedRoleResponse(UserRole userRole);

    @Mapping(target = "roleId",source ="role.id" )
    @Mapping(target = "userId",source = "user.id")
    UpdatedUserRoleResponse userRoleToUpdatedUserRoleResponse(UserRole userRole);

}
