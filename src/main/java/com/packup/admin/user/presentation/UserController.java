package com.packup.admin.user.presentation;

import com.packup.admin.auth.annotation.Auth;
import com.packup.admin.user.dto.UserResponse;
import com.packup.admin.user.dto.UserSearchRequest;
import com.packup.admin.user.dto.UserStatusUpdateRequest;
import com.packup.admin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getUsers(
            @ModelAttribute UserSearchRequest request,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<UserResponse> response = userService.getUsers(request, pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updateUserStatus(
            @Auth Long memberId,
            @PathVariable Long userId,
            @RequestBody UserStatusUpdateRequest request
    ) {
        userService.updateUserStatus(memberId, userId, request);
        return ResponseEntity.ok().build();
    }


}