package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.model.Resume;
import com.yudiol.JobSearchPlatformBack.repository.ResumeRepository;
import com.yudiol.JobSearchPlatformBack.service.GeneratedPdfResumeService;
import com.yudiol.JobSearchPlatformBack.util.GeneratePdf;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeneratedPdfResumeServiceImpl implements GeneratedPdfResumeService {

    private final GeneratePdf generatePdf;
    private final ResumeRepository resumeRepository;

    public ResumePdfResponseDto createPdf(String userId) {
        Resume resume = resumeRepository.findByUserId(userId).getResume();
        byte[] pdf = null;
        try {
            String path = "resumes/resume-" + userId + ".pdf";
            generatePdf.downloadPdf(resume, userId);
            Path pdfPath = Paths.get(path);
            pdf = Files.readAllBytes(pdfPath);
            Thread.sleep(100);
//            File myFile = new File(path);
//            myFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResumePdfResponseDto(pdf);
    }
}
