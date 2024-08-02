package com.turkcell.gyt.managementService.core.dtos.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatedRegisterResponse {
    private UUID id;
    private String email;
    private String password;
}
