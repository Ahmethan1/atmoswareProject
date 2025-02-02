package com.turkcell.gyt.managementService.core.dtos.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatedResgisterRequest {
    private UUID id;
    private String email;
    private String password;
}
