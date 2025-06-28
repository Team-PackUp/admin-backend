package com.packup.admin.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.packup.admin.user.domain.UserInfo;
import com.packup.admin.user.domain.UserPrefer;

import java.util.Optional;

public interface UserPreferRepository extends JpaRepository<UserPrefer, Long> {
    Optional<UserPrefer> findByUser(UserInfo user);
}
