package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.dto.ResumeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResumeRepository extends MongoRepository<ResumeDto, String> {
    ResumeDto findByUserId(String userId);

    void deleteAllByUserId(String userId);
}
