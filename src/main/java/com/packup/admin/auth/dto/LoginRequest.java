package com.packup.admin.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String adminId;
    private String adminPassword;
}
