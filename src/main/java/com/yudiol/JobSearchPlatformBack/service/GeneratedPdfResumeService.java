package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;

public interface GeneratedPdfResumeService {

    ResumePdfResponseDto createPdf(String userId);
}
