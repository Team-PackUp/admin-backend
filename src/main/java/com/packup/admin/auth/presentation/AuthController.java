package com.packup.admin.auth.presentation;

import com.packup.admin.auth.annotation.Auth;
import com.packup.admin.auth.dto.*;
import com.packup.admin.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${jwt.refresh-expiration}")
    private long refreshExpirationMillis;

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        AuthToken authToken = authService.login(request);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", authToken.refreshToken())
                .httpOnly(true)
                .secure(true) // HTTPS 환경에서만 true 로컬이면 false
                .path("/")
                .maxAge(refreshExpirationMillis / 1000)
                .sameSite("None") // Strict
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(new LoginResponse(authToken.accessToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@CookieValue("refreshToken") String refreshToken) {
        String accessToken = authService.refresh(refreshToken);
        return ResponseEntity.ok(new RefreshTokenResponse(accessToken));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@Auth Long userId, HttpServletResponse response) {
        authService.logout(userId);

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)  // https true local false
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());
        return ResponseEntity.ok().build();
    }
}
