package com.packup.admin.guide.dto;

import java.util.List;

public record GuideApplicationSearchRequest(
        String email,
        String nickname,
        String seq,
        List<String> statuses
) {}
