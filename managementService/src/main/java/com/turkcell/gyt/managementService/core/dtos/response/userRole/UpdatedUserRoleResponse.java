package com.turkcell.gyt.managementService.core.dtos.response.userRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedUserRoleResponse {
    private UUID id;
    private UUID userId;
    private UUID roleId;
}
