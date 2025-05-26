package com.packup.admin.auth.presentation;

import com.packup.admin.auth.dto.LoginRequest;
import com.packup.admin.auth.dto.LoginResponse;
import com.packup.admin.auth.dto.RefreshTokenRequest;
import com.packup.admin.auth.dto.RefreshTokenResponse;
import com.packup.admin.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request) {
        String accessToken = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(new RefreshTokenResponse(accessToken));
    }
}
