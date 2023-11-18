package com.yudiol.JobSearchPlatformBack.mapper;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.model.UploadedPdfResume;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    ResumePdfResponseDto toPdfResumeDto(UploadedPdfResume pdfResume);

}