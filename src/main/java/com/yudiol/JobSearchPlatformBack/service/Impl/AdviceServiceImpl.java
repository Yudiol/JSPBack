package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.AdviceResponseDto;
import com.yudiol.JobSearchPlatformBack.exception.errors.NotFoundException;
import com.yudiol.JobSearchPlatformBack.model.Advice;
import com.yudiol.JobSearchPlatformBack.repository.AdviceRepository;
import com.yudiol.JobSearchPlatformBack.service.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService {

    private final AdviceRepository adviceRepository;

    public AdviceResponseDto getAdvice(Long id) {
        Advice advice = adviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Совет", String.valueOf(id)));
        return new AdviceResponseDto(advice.getAdvice());
    }
}
