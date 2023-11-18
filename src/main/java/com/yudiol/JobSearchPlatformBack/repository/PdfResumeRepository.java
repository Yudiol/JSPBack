package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.model.PdfResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PdfResumeRepository extends JpaRepository<PdfResume, Long> {

    Optional<PdfResume> findByUserId(String id);
}
