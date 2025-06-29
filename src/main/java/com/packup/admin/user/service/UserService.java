package com.packup.admin.user.service;

import com.packup.admin.admin.domain.repository.AdminInfoRepository;
import com.packup.admin.user.domain.UserDetailInfo;
import com.packup.admin.user.domain.UserInfo;
import com.packup.admin.user.domain.repository.UserInfoRepository;
import com.packup.admin.user.dto.UserResponse;
import com.packup.admin.user.dto.UserSearchRequest;
import com.packup.admin.user.dto.UserStatusUpdateRequest;
import com.packup.admin.user.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.packup.admin.user.exception.UserExceptionType.NOT_FOUND_MEMBER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final AdminInfoRepository adminInfoRepository;

    public Page<UserResponse> getUsers(UserSearchRequest request, Pageable pageable) {
        if (request.email() != null && !request.email().isBlank()) {
            return userInfoRepository.findByEmailContaining(request.email(), pageable);
        }
        if (request.nickname() != null && !request.nickname().isBlank()) {
            return userInfoRepository.findByNicknameContaining(request.nickname(), pageable);
        }
        if (request.seq() != null && !request.seq().isBlank()) {
            return userInfoRepository.findBySeqContaining(request.seq(), pageable);
        }

        return userInfoRepository.findAllUserResponses(pageable);
    }

    @Transactional
    public void updateUserStatus(Long memberId, Long userId, UserStatusUpdateRequest request) {
        UserInfo user = userInfoRepository.findById(userId)
                .orElseThrow(() -> new UserException(NOT_FOUND_MEMBER));
        UserDetailInfo detail = user.getDetailInfo();

        if (request.shouldUpdateBan()) {
            if (request.isBanned()) {
                user.ban();
                detail.recordBanInfo(request.banReason(), memberId);
            } else {
                user.unban();
                detail.clearBanInfo();
            }
        }

        if (request.shouldUpdateWithdraw()) {
            if (request.isWithdrawn()) {
                user.withdraw();
            } else {
                user.restore();
            }
        }
    }

}