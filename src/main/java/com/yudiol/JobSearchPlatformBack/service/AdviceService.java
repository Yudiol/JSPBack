package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.dto.AdviceResponseDto;

public interface AdviceService {
    AdviceResponseDto getAdvice(Long id);
}
