package com.packup.admin.user.service;

import com.packup.admin.user.domain.repository.UserInfoRepository;
import com.packup.admin.user.dto.UserResponse;
import com.packup.admin.user.dto.UserSearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;

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
}