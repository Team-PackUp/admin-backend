package com.packup.admin.admin.domain.repository;

import com.packup.admin.admin.domain.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminInfoRepository extends JpaRepository<AdminInfo, Long> {
    Optional<AdminInfo> findByAdminId(String adminId);
}
