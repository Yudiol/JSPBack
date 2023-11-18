package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.model.PdfResume;
import com.yudiol.JobSearchPlatformBack.repository.PdfResumeRepository;
import com.yudiol.JobSearchPlatformBack.service.PdfResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfResumeServiceImpl implements PdfResumeService {
    private final PdfResumeRepository pdfResumeRepository;

    public PdfResume findById(String id) {
        return pdfResumeRepository.findByUserId(id).orElse(null);
    }
}
