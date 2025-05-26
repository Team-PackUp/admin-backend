package com.packup.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}
