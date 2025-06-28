package com.packup.admin.user.dto;

public record UserSearchRequest(
        String seq,
        String email,
        String nickname
) {}
