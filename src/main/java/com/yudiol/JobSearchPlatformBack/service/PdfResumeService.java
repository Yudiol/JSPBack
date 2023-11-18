package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfResumeService {
    ResumePdfResponseDto findByUserId(String userId);

    void savePdf(String userId, MultipartFile file) throws IOException;

    void saveBytes(String id, byte[] bytes);
    void deleteByUserId(String userId);
}
