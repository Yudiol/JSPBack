package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.ResumeDto;
import com.yudiol.JobSearchPlatformBack.model.Resume;
import com.yudiol.JobSearchPlatformBack.repository.ResumeRepository;
import com.yudiol.JobSearchPlatformBack.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    public Resume findByUserId(String id) {
        return resumeRepository.findByUserId(id).getResume();
    }

    @Transactional
    public void save(String userId, Resume resume) {
        ResumeDto resumeDto = new ResumeDto(userId, resume);
        resumeRepository.deleteAllByUserId(userId);
        resumeRepository.save(resumeDto);
    }

    @Transactional
    public void deleteByUserId(String userId) {
        resumeRepository.deleteAllByUserId(userId);
    }
}
