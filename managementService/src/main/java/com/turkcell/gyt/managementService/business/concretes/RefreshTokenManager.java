package com.turkcell.gyt.managementService.business.concretes;

import com.turkcell.gyt.managementService.business.abstracts.RefreshTokenService;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;
import com.turkcell.gyt.managementService.dataAccess.RefreshTokenRepository;
import com.turkcell.gyt.managementService.entity.RefreshToken;
import com.turkcell.gyt.managementService.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RefreshTokenManager implements RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;
    @Override
    public RefreshToken create(User user) {
        RefreshToken token=new RefreshToken();
        token.setUser(user);
        token.setToken("abc");//TODO:Refactor(use jwtService.generateToken()with longer expriration time)
        token.setExpirationDate(LocalDateTime.now().plusDays(10));
        refreshTokenRepository.save(token);
        return token;
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken=refreshTokenRepository
                .findByToken(token)

                .orElseThrow(()->new BusinessException("Refresh token not found"));

        //Tüm şartlar sağlanırsa
        if(refreshToken.getExpirationDate().isBefore(LocalDateTime.now()))
            throw new BusinessException("Refresh token expired.Please login again.");
        if(refreshToken.getRevokedDate()!=null)
            throw new BusinessException("Refresh token revoked.");
        return refreshToken;
    }
    private void revokeAllTokens(User user){
        // TODO:revoke
    }
}
