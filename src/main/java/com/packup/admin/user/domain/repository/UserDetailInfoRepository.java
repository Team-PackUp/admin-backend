package com.packup.admin.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.packup.admin.user.domain.UserDetailInfo;
import com.packup.admin.user.domain.UserInfo;

import java.util.Optional;

public interface UserDetailInfoRepository extends JpaRepository<UserDetailInfo, Long> {
    Optional<UserDetailInfo> findByUser(UserInfo user);
}
