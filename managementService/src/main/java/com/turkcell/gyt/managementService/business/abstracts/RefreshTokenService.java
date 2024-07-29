package com.turkcell.gyt.managementService.business.abstracts;

import com.turkcell.gyt.managementService.entity.RefreshToken;
import com.turkcell.gyt.managementService.entity.User;


public interface RefreshTokenService {
    RefreshToken create(User user);
    RefreshToken verifyRefreshToken(String token);
}
