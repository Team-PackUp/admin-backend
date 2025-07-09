package com.packup.admin.guide.service;


import com.packup.admin.common.domain.repository.CommonCodeRepository;
import com.packup.admin.guide.domain.GuideApplication;
import com.packup.admin.guide.domain.repository.GuideApplicationRepository;
import com.packup.admin.guide.dto.GuideApplicationResponse;
import com.packup.admin.guide.dto.GuideApplicationSearchRequest;
import com.packup.admin.guide.exception.GuideException;
import com.packup.admin.guide.exception.GuideExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideApplicationRepository guideRepository;
    private final CommonCodeRepository commonCodeRepository;

    public Page<GuideApplicationResponse> getApplications(
            GuideApplicationSearchRequest request,
            Pageable pageable
    ) {
        List<String> codeIds = request.statuses().stream()
                .map(name -> commonCodeRepository.findByCodeName(name)
                        .orElseThrow(() -> new GuideException(GuideExceptionType.NOT_FOUND_APPLICATION))
                        .getCodeId()
                )
                .toList();

        if (request.email() != null && !request.email().isBlank()) {
            return guideRepository.findByEmailContaining(request.email(), codeIds, pageable);
        }

        if (request.nickname() != null && !request.nickname().isBlank()) {
            return guideRepository.findByNicknameContaining(request.nickname(), codeIds, pageable);
        }

        if (request.seq() != null && !request.seq().isBlank()) {
            return guideRepository.findBySeqContaining(request.seq(), codeIds, pageable);
        }

        return guideRepository.findAllByStatuses(codeIds, pageable);
    }


//    public void updateStatus(Long id, String status) {
//        GuideApplication app = guideRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("신청 내역이 존재하지 않습니다."));
//        app.setStatusCode(status); // setter 또는 커맨드 메서드 필요
//    }
}
