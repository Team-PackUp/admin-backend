package com.packup.admin.guide.presentation;

import com.packup.admin.guide.dto.GuideApplicationDetailResponse;
import com.packup.admin.guide.dto.GuideApplicationResponse;
import com.packup.admin.guide.dto.GuideApplicationSearchRequest;
import com.packup.admin.guide.service.GuideService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guides")
public class GuideController {

    private final GuideService guideService;

    @GetMapping("/applications")
    public ResponseEntity<Page<GuideApplicationResponse>> getApplications(
            @ModelAttribute GuideApplicationSearchRequest request,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        Page<GuideApplicationResponse> result = guideService.getApplications(request, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<GuideApplicationDetailResponse> getApplicationDetail(
            @PathVariable Long id
    ) {
        GuideApplicationDetailResponse response = guideService.getApplicationDetail(id);
        return ResponseEntity.ok(response);
    }



}
