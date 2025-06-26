package com.packup.admin.system.domain.repository;

import com.packup.admin.common.enums.YnType;
import com.packup.admin.system.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByDeleteFlag(YnType deleteFlag, Pageable pageable);
}
