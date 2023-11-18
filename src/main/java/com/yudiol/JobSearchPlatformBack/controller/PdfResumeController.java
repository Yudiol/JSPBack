package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.ResumeBytesRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.service.PdfResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/pdf")
public class PdfResumeController {
    private final PdfResumeService pdfResumeService;

    @GetMapping("/uploaded/{userId}")
    public ResumePdfResponseDto getUploadedResume(@PathVariable("userId") String userId) {
        return pdfResumeService.findByUserId(userId);
    }

    @PostMapping("/uploadedPdf/{userId}")
    public void savePdfResume(@PathVariable("userId") String userId, @RequestParam("file") MultipartFile file) throws IOException {
        pdfResumeService.savePdf(userId, file);
    }

    @PostMapping("/uploadedBytes/{userId}")
    public void saveBytesResume(@PathVariable("userId") String userId, @RequestBody ResumeBytesRequestDto requestDto) {
        pdfResumeService.saveBytes(userId, requestDto.getBytes());
    }

    @DeleteMapping("/deleteUploaded/{userId}")
    public void deleteUploadedResume(@PathVariable("userId") String userId) {
        pdfResumeService.deleteByUserId(userId);
    }
}
