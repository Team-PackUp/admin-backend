package com.packup.admin.system.service;

import com.packup.admin.common.domain.CommonCode;
import com.packup.admin.common.domain.repository.CommonCodeRepository;
import com.packup.admin.common.enums.YnType;
import com.packup.admin.system.domain.Notice;
import com.packup.admin.system.domain.SystemSetting;
import com.packup.admin.system.domain.repository.NoticeRepository;
import com.packup.admin.system.domain.repository.SystemSettingRepository;
import com.packup.admin.system.dto.NoticeRequest;
import com.packup.admin.system.dto.SystemSettingResponse;
import com.packup.admin.system.exception.SystemSettingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.packup.admin.system.exception.SystemSettingExceptionType.NOT_FOUND_SETTING;

@Service
@RequiredArgsConstructor
public class SystemSettingService {

    private final SystemSettingRepository systemSettingRepository;
    private final CommonCodeRepository commonCodeRepository;
    private final NoticeRepository noticeRepository;


    @Transactional
    public SystemSettingResponse getSetting() {
        SystemSetting setting = systemSettingRepository.findById(1L)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING));

        String codeId = setting.getLanguage();

        String languageName = commonCodeRepository.findByCodeId(codeId)
                .map(CommonCode::getCodeName)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING));

        return new SystemSettingResponse(languageName);
    }

    @Transactional
    public void updateLanguage(String language) {
        String code = commonCodeRepository.findByCodeName(language)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING))
                .getCodeId();
        SystemSetting setting = systemSettingRepository.findById(1L).orElseThrow();

        setting.updateLanguage(code);
    }

    @Transactional
    public void createNotice(Long memberId, NoticeRequest request) {
        String codeName = request.isUrgent() ? "긴급" : "일반";

        String noticeTypeCode = commonCodeRepository.findByCodeName(codeName)
                .map(CommonCode::getCodeId)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING));

            Notice notice = Notice.of(
                request.title(),
                request.content(),
                memberId,
                noticeTypeCode,
                request.sendFcm() ? YnType.Y : YnType.N,
                YnType.N
        );

        noticeRepository.save(notice);
    }
}
