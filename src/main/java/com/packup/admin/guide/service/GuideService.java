package com.packup.admin.guide.service;


import com.packup.admin.common.domain.CommonCode;
import com.packup.admin.common.domain.repository.CommonCodeRepository;
import com.packup.admin.guide.domain.GuideApplication;
import com.packup.admin.guide.domain.repository.GuideApplicationRepository;
import com.packup.admin.guide.dto.GuideApplicationDetailResponse;
import com.packup.admin.guide.dto.GuideApplicationResponse;
import com.packup.admin.guide.dto.GuideApplicationSearchRequest;
import com.packup.admin.guide.exception.GuideException;
import com.packup.admin.guide.exception.GuideExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public GuideApplicationDetailResponse getApplicationDetail(Long id) {
        GuideApplication application = guideRepository.findById(id)
                .orElseThrow(() -> new GuideException(GuideExceptionType.NOT_FOUND_APPLICATION));

        String codeName = commonCodeRepository.findByCodeId(application.getStatusCode())
                .map(CommonCode::getCodeName)
                .orElseThrow(() -> new GuideException(GuideExceptionType.NOT_FOUND_APPLICATION));

        // 이미지는 S3 등 외부 저장소에 있고 바로 보여줄 수 있다고 가정
        // 신분증 관련 이미지니까 보안 관련 이슈 있을 수 있음
        return GuideApplicationDetailResponse.builder()
                .seq(application.getSeq())
                .status(codeName)
                .idImageUrl(application.getIdDocumentUrl())
                .content(application.getContent())
                .build();
    }

    @Transactional
    public void approveApplication(Long memberId, Long id) {
    }

    @Transactional
    public void updateApplicationStatus(Long memberId, Long id, String status) {
        GuideApplication application = guideRepository.findById(id)
                .orElseThrow(() -> new GuideException(GuideExceptionType.NOT_FOUND_APPLICATION));

        if (!application.getStatusCode().equals("060001")) {
            throw new GuideException(GuideExceptionType.ALREADY_PROCESSED_APPLICATION);
        }

        if (status.equals("APPROVED")) {
            throw new GuideException(GuideExceptionType.INVALID_REQUEST);
        }

        String statusCode = commonCodeRepository.findByCodeName(status)
                .orElseThrow(() -> new GuideException(GuideExceptionType.NOT_FOUND_APPLICATION))
                .getCodeId();

        application.updateStatus(statusCode, memberId);
    }




}
