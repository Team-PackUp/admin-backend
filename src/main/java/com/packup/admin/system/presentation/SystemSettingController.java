package com.packup.admin.system.presentation;

import com.packup.admin.system.domain.SystemSetting;
import com.packup.admin.system.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/system")
public class SystemSettingController {

    private final SystemSettingService systemSettingService;
//    private final

    @GetMapping
    public ResponseEntity<SystemSetting> getSystemSetting() {
        return ResponseEntity.ok(systemSettingService.getSetting());
    }

    @PutMapping("/language")
    public ResponseEntity<Void> updateLanguage(@RequestParam String language) {
        systemSettingService.updateLanguage(language);
        return ResponseEntity.ok().build();
    }
}
