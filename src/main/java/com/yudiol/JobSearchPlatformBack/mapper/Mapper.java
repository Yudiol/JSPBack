package com.yudiol.JobSearchPlatformBack.mapper;

import com.yudiol.JobSearchPlatformBack.dto.LinkDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.model.Link;
import com.yudiol.JobSearchPlatformBack.model.Response;
import com.yudiol.JobSearchPlatformBack.model.UploadedPdfResume;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
    ResumePdfResponseDto toPdfResumeDto(UploadedPdfResume pdfResume);

    LinkDto toLinkDto(Link link);

    ResponseTableResponseDto toResponseTable(Response response);

    Response toResponse(ResponseTableResponseDto response);

    Response toResponse(ResponseTableRequestDto response);
}