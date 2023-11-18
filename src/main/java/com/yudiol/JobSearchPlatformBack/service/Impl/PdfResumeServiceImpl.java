package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.mapper.ResumeMapper;
import com.yudiol.JobSearchPlatformBack.model.UploadedPdfResume;
import com.yudiol.JobSearchPlatformBack.repository.PdfResumeRepository;
import com.yudiol.JobSearchPlatformBack.service.PdfResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PdfResumeServiceImpl implements PdfResumeService {
    private final PdfResumeRepository pdfResumeRepository;
    private final ResumeMapper resumeMapper;

    public ResumePdfResponseDto findByUserId(String userId) {
        UploadedPdfResume pdfResume = pdfResumeRepository.findByUserId(userId).orElse(null);
        return resumeMapper.toPdfResumeDto(pdfResume);
    }

    @Transactional
    public void savePdf(String userId, MultipartFile file) throws IOException {
        saveOrReplaceResumeIfExists(userId, file.getBytes());
    }

    @Transactional
    public void saveBytes(String userId, byte[] bytes) {
        saveOrReplaceResumeIfExists(userId, bytes);
    }

    @Transactional
    public void deleteByUserId(String userId) {
        System.out.println(userId);
        pdfResumeRepository.deleteByUserId(userId);
    }

    private UploadedPdfResume createUploadedResume(String userId, byte[] bytes) {
        UploadedPdfResume resume = new UploadedPdfResume();
        resume.setUserId(userId);
        resume.setBytes(bytes);
        return resume;
    }

    private void saveOrReplaceResumeIfExists(String userId, byte[] bytes) {
        Optional<UploadedPdfResume> optional = pdfResumeRepository.findByUserId(userId);
        UploadedPdfResume resume;
        if (optional.isEmpty()) {
            resume = createUploadedResume(userId, bytes);
            pdfResumeRepository.save(resume);
        } else {
            resume = optional.orElse(null);
            resume.setBytes(bytes);
            pdfResumeRepository.save(resume);
        }
    }
}
