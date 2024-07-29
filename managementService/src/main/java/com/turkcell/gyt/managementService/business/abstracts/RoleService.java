package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.CreateRoleRequest;
import com.turkcell.gyt.managementService.core.dtos.response.CreatedRoleResponse;
import com.turkcell.gyt.managementService.core.dtos.response.GetByIdRoleResponse;

import java.util.UUID;

public interface RoleService {
    CreatedRoleResponse createRole(CreateRoleRequest createRoleRequest);
    GetByIdRoleResponse getById(UUID id);
}
