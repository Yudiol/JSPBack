package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.dto.ResponseTableListResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableResponseDto;

public interface ResponseTableService {
    void save(String userId, ResponseTableRequestDto response);

    ResponseTableListResponseDto findAll(String userId);

    void deleteByIdAndUserId(String userId, Long id);

    void update(String userId, ResponseTableResponseDto response);
}
