package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.ResponseTableListResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableResponseDto;
import com.yudiol.JobSearchPlatformBack.mapper.Mapper;
import com.yudiol.JobSearchPlatformBack.model.Response;
import com.yudiol.JobSearchPlatformBack.repository.ResponseTableRepository;
import com.yudiol.JobSearchPlatformBack.service.ResponseTableService;
import com.yudiol.JobSearchPlatformBack.util.ResponseDateComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResponseTableServiceImpl implements ResponseTableService {

    private final Mapper mapper;
    private final ResponseTableRepository repository;

    @Transactional
    public void save(String userId, ResponseTableRequestDto responseTableRequestDto) {
        Response response = mapper.toResponse(responseTableRequestDto);
        response.setResponseDate(LocalDateTime.now());
        response.setUserId(userId);
        repository.save(response);
    }

    public ResponseTableListResponseDto findAll(String userId) {
        List<ResponseTableResponseDto> list =
                repository.findAllByUserId(userId)
                        .map(responses -> responses.stream()
                                .sorted(new ResponseDateComparator())
                                .map(mapper::toResponseTable)
                                .collect(Collectors.toList()))
                        .orElseThrow();
        return new ResponseTableListResponseDto(list);
    }

    @Transactional
    public void deleteByIdAndUserId(String userId, Long id) {
        repository.deleteByIdAndByUserId(userId, id);
    }

    @Transactional
    public void update(String userId, ResponseTableResponseDto responseTableResponseDto) {
        Response r = mapper.toResponse(responseTableResponseDto);

        repository.update(userId, r.getId(), r.getName(), r.getLink(), r.getPosition(), r.getContact(), r.getStatus(), r.getComments());
    }
}
