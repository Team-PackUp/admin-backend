package com.packup.admin.system.dto;

import com.packup.admin.common.enums.YnType;
import com.packup.admin.system.domain.Notice;

import java.time.LocalDateTime;
import java.util.Map;

public record NoticeResponse(
        String id,
        String title,
        Map<String, Object> content,
        boolean sendFcm,
        boolean isUrgent,
        LocalDateTime createdAt
) {
    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(
                String.valueOf(notice.getSeq()),
                notice.getTitle(),
                notice.getContent(),
                notice.getFcmFlag() == YnType.Y,
                notice.getNoticeType().equals("000101"),
                notice.getCreatedAt()
        );
    }
}
