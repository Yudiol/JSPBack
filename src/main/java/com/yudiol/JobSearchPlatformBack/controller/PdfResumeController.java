package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.PdfResponseDto;
import com.yudiol.JobSearchPlatformBack.service.PdfResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PdfResumeController {
    private final PdfResumeService pdfResumeService;

    @GetMapping("/uploadedPdf/{id}")
    public PdfResponseDto getPdfResume(@PathVariable ("id") String id) {
        return pdfResumeService.findById(id);
    }
}
