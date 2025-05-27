package com.packup.admin.auth.service;

import com.packup.admin.admin.domain.AdminInfo;
import com.packup.admin.admin.domain.repository.AdminInfoRepository;
import com.packup.admin.auth.domain.RefreshToken;
import com.packup.admin.auth.domain.repository.RefreshTokenRepository;
import com.packup.admin.auth.dto.AuthToken;
import com.packup.admin.auth.dto.LoginRequest;
import com.packup.admin.auth.dto.LoginResponse;
import com.packup.admin.auth.exception.AuthException;
import com.packup.admin.auth.exception.AuthExceptionType;
import com.packup.admin.config.security.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.packup.admin.auth.exception.AuthExceptionType.EXPIRED_REFRESH_TOKEN;
import static com.packup.admin.auth.exception.AuthExceptionType.INVALID_REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminInfoRepository adminInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public AuthToken login(LoginRequest request) {
        String adminId = request.getUsername();
        String password = request.getPassword();

        AdminInfo admin = adminInfoRepository.findByAdminId(adminId)
                .orElseThrow(() -> new AuthException(AuthExceptionType.NOT_FOUND_MEMBER));

        if (!passwordEncoder.matches(password, admin.getAdminPassword())) {
            throw new AuthException(AuthExceptionType.NOT_FOUND_MEMBER);
        }

        String accessToken = jwtTokenProvider.createToken(admin.getAdminId());
        String refreshToken = jwtTokenProvider.createRefreshToken(admin.getAdminId());

        refreshTokenRepository.findByUserId(admin.getSeq())
                .ifPresentOrElse(
                        token -> token.updateToken(refreshToken, jwtTokenProvider.getRefreshTokenExpiryDate()),
                        () -> refreshTokenRepository.save(
                                RefreshToken.builder()
                                        .userId(admin.getSeq())
                                        .token(refreshToken)
                                        .expiresAt(jwtTokenProvider.getRefreshTokenExpiryDate())
                                        .build()
                        )
                );

        return new AuthToken(accessToken, refreshToken);
    }

    public String refresh(String refreshToken) {
        Claims claims = jwtTokenProvider.parseClaims(refreshToken);

        String type = claims.get("type", String.class);
        if (!"refresh".equals(type)) {
            throw new AuthException(INVALID_REFRESH_TOKEN);
        }

        Long userSeq = Long.parseLong(claims.getSubject());

        RefreshToken savedToken = refreshTokenRepository.findByUserId(userSeq)
                .orElseThrow(() -> new AuthException(EXPIRED_REFRESH_TOKEN));

        if (!savedToken.getToken().equals(refreshToken)) {
            throw new AuthException(INVALID_REFRESH_TOKEN);
        }

        if (claims.getExpiration().before(new Date())) {
            throw new AuthException(EXPIRED_REFRESH_TOKEN);
        }


        return jwtTokenProvider.createToken(String.valueOf(savedToken.getUserId()));
    }

    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}