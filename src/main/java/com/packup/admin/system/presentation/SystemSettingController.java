package com.packup.admin.system.presentation;

import com.packup.admin.system.domain.SystemSetting;
import com.packup.admin.system.dto.SystemSettingResponse;
import com.packup.admin.system.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system")
public class SystemSettingController {

    private final SystemSettingService systemSettingService;

    @GetMapping
    public ResponseEntity<SystemSettingResponse> getSystemSetting() {
        return ResponseEntity.ok(systemSettingService.getSetting());
    }

    @PutMapping("/language")
    public ResponseEntity<Void> updateLanguage(@RequestParam String language) {
        systemSettingService.updateLanguage(language);
        return ResponseEntity.ok().build();
    }
}
