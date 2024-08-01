package com.turkcell.gyt.managementService.core.dtos.response.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedUserRoleResponse {
    private UUID userId;
    private UUID roleId;
}
