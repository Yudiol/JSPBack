package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.model.Resume;

public interface ResumeService {

    Resume findByUserId(String id);

    void save(String userId, Resume resume);

    void deleteByUserId(String userId);
}
