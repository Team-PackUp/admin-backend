package com.packup.admin.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record NoticeRequest(
        String title,
        Map<String, Object> content,
        @JsonProperty("sendFcm") boolean sendFcm,
        @JsonProperty("isUrgent") boolean isUrgent
) {}