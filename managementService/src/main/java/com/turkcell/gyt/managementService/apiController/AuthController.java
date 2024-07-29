package com.turkcell.gyt.managementService.apiController;

import com.turkcell.gyt.managementService.business.abstracts.AuthService;
import com.turkcell.gyt.managementService.core.dtos.request.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController{
    private final AuthService authService;

    @PostMapping("/login")
    private String login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Cookie httpOnlyCookie = new Cookie("refreshToken", "abc");
        httpOnlyCookie.setHttpOnly(true);
        httpOnlyCookie.setMaxAge(10 * 24 * 60 * 60);
        response.addCookie(httpOnlyCookie);
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request) {
        String refreshToken = getCookie(request, "refreshToken");
        return authService.refreshToken(refreshToken);
    }
}
