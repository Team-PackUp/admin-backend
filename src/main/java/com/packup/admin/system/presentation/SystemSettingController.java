package com.packup.admin.system.presentation;

import com.packup.admin.auth.annotation.Auth;
import com.packup.admin.system.domain.SystemSetting;
import com.packup.admin.system.dto.NoticeRequest;
import com.packup.admin.system.dto.SystemSettingRequest;
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
    public ResponseEntity<Void> updateLanguage(@RequestBody SystemSettingRequest request) {
        systemSettingService.updateLanguage(request.language());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notice")
    public ResponseEntity<Void> createNotice(@Auth Long memberId, @RequestBody NoticeRequest request) {
        systemSettingService.createNotice(memberId, request);
        return ResponseEntity.ok().build();
    }


}
