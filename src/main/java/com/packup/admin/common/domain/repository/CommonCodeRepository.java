package com.packup.admin.common.domain.repository;

import com.packup.admin.common.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, String> {
    // 중복이면 2개 나올 듯
    Optional<CommonCode> findByCodeName(String codeName);
    Optional<CommonCode> findByCodeId(String codeId);
}
