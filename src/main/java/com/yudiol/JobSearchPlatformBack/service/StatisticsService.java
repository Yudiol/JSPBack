package com.yudiol.JobSearchPlatformBack.service;

public interface StatisticsService {
    void save(String userId, Long responseId, Integer status);

    void update(Long responseId, String userId, Integer status);
}
