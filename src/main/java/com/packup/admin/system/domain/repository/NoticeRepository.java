package com.packup.admin.system.domain.repository;

import com.packup.admin.system.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
