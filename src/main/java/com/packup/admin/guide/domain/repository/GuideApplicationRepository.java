package com.packup.admin.guide.domain.repository;

import com.packup.admin.guide.domain.GuideApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideApplicationRepository extends JpaRepository<GuideApplication, Long> {

}
