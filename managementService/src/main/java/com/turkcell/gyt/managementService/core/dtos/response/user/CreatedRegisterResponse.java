package com.turkcell.gyt.managementService.core.dtos.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedRegisterResponse {
    private UUID id;
    private String email;
}
