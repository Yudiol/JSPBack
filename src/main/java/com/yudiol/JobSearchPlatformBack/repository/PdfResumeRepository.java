package com.yudiol.JobSearchPlatformBack.repository;

import com.yudiol.JobSearchPlatformBack.model.UploadedPdfResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PdfResumeRepository extends JpaRepository<UploadedPdfResume, Long> {

    Optional<UploadedPdfResume> findByUserId(String userId);

    void deleteByUserId(String userId);
}
