package com.packup.admin.user.dto;

public record UserStatusUpdateRequest(
        Boolean ban,
        String banReason,
        Boolean withdraw
) {
    public boolean shouldUpdateBan() {
        return ban != null;
    }

    public boolean isBanned() {
        return Boolean.TRUE.equals(ban);
    }

    public boolean shouldUpdateWithdraw() {
        return withdraw != null;
    }

    public boolean isWithdrawn() {
        return Boolean.TRUE.equals(withdraw);
    }
}
