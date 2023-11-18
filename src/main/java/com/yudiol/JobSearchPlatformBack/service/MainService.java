package com.yudiol.JobSearchPlatformBack.service;

import com.yudiol.JobSearchPlatformBack.dto.MainResponseDto;

import java.sql.SQLException;

public interface MainService {
    MainResponseDto getMainResponseDto(String id, Integer month, Integer year) throws SQLException;
}
