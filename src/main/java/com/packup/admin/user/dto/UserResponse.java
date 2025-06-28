package com.packup.admin.user.dto;

import com.packup.admin.common.enums.YnType;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String nickname,
        String joinType,
        Integer age,
        String nation,
        YnType banFlag,
        String banReason,
        Long banAdminId,
        YnType withdrawFlag,
        LocalDateTime createdAt
) {}
