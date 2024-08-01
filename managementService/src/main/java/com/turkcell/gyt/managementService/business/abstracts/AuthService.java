package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.core.dtos.request.user.LoginRequest;

public interface AuthService {
    String login(LoginRequest request);
    String refreshToken(String refreshToken);
}
