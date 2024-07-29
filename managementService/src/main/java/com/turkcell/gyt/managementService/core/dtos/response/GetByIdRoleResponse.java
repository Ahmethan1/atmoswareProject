package com.turkcell.gyt.managementService.core.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdRoleResponse {
    private UUID id;
    private String name;
}
