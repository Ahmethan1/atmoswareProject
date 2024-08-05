package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.role.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.role.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.role.GetByIdRoleResponse;
import com.turkcell.gyt.managementService.entity.Role;

import java.util.UUID;

public interface RoleService {
    CreatedRoleResponse createRole(CreateRoleRequest createRoleRequest);
    GetByIdRoleResponse getById(UUID id);
    Role getRoleById(UUID id);
    String getRoleNameById(UUID id);
}
