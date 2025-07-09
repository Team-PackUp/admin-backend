package com.packup.admin.guide.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record GuideApplicationDetailResponse(
        Long seq,
        String status,
        String idImageUrl,
        Map<String, Object> content
) {}
