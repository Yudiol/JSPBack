package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.dto.ResumeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResumeRepository extends MongoRepository<ResumeDto, String> {
    Optional<ResumeDto> findByUserId(String userId);

    void deleteAllByUserId(String userId);
}
