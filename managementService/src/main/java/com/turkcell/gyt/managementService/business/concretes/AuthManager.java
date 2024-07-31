package com.turkcell.gyt.managementService.business.concretes;

import com.atmosware.core.utils.JwtService;
import com.turkcell.gyt.managementService.business.abstracts.AuthService;
import com.turkcell.gyt.managementService.business.abstracts.RefreshTokenService;
import com.turkcell.gyt.managementService.business.abstracts.UserService;
import com.turkcell.gyt.managementService.business.messages.AuthMessages;
import com.turkcell.gyt.managementService.core.dtos.request.LoginRequest;
import com.turkcell.gyt.managementService.core.utilitiy.exceptions.types.BusinessException;

import com.turkcell.gyt.managementService.entity.RefreshToken;
import com.turkcell.gyt.managementService.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public String login(LoginRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (!authentication.isAuthenticated()) throw new BusinessException(AuthMessages.LOGIN_FAILED);

        User user= userService.findByUsername(request.getEmail());
        String jwt=generateJwt(user);

        //TODO:Create Refresh Token For Specific USER
        refreshTokenService.create(user);
        return jwt;
    }
    @Override
    public String refreshToken(String refreshToken) {
        //TODO:RefreshToken-User
        RefreshToken token= refreshTokenService.verifyRefreshToken(refreshToken);
        User user =token.getUser();
        return generateJwt(user);
    }
    private String generateJwt(User user){
        Map<String,Object> claims =new HashMap<>();
        claims.put("username",user.getUsername());
        claims.put("id",user.getId());
        return jwtService.generateToken(claims,user.getEmail());

    }
}
