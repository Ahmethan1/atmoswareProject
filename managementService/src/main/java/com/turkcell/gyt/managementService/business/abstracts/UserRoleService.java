package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.userRole.CreateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.request.userRole.UpdateUserRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedUserRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.userRole.UpdatedUserRoleResponse;
import com.turkcell.gyt.managementService.entity.Role;
import com.turkcell.gyt.managementService.entity.User;

import java.util.UUID;

public interface UserRoleService {
    CreatedUserRoleResponse add(CreateUserRoleRequest createUserRoleRequest);
    UpdatedUserRoleResponse update(UpdateUserRoleRequest updateUserRoleRequest);
    UUID getRoleByUser(User user);
}
