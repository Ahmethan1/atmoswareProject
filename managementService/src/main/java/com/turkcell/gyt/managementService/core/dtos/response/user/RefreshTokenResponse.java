package com.turkcell.gyt.managementService.core.dtos.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
}
