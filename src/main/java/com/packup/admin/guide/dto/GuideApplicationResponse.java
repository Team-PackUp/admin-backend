package com.packup.admin.guide.dto;

import java.time.LocalDateTime;

public record GuideApplicationResponse(
        Long id,
        String email,
        String nickname,
        int age,
        String nation,
        String status,
        LocalDateTime createdAt
) {}
