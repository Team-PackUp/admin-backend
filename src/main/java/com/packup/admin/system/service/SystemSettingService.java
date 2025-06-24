package com.packup.admin.system.service;

import com.packup.admin.common.domain.repository.CommonCodeRepository;
import com.packup.admin.system.domain.SystemSetting;
import com.packup.admin.system.domain.repository.SystemSettingRepository;
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

    public SystemSetting getSetting() {
        return systemSettingRepository.findById(1L)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING));
    }

    @Transactional
    public void updateLanguage(String language) {
        String code = commonCodeRepository.findByCodeName(language)
                .orElseThrow(() -> new SystemSettingException(NOT_FOUND_SETTING))
                .getCodeId();
        SystemSetting setting = getSetting();

        setting.updateLanguage(code);
    }
}
