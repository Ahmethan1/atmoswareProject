package com.turkcell.gyt.managementService.core.dtos.request.userRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRoleRequest {
    private UUID id;
    private UUID userId;
    private UUID roleId;
}
