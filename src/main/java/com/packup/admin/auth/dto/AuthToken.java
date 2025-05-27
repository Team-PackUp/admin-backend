package com.packup.admin.auth.dto;

public record AuthToken(
        String accessToken,
        String refreshToken) {

}
