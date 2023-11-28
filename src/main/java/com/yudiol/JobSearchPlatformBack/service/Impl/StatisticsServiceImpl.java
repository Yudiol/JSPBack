package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.exception.errors.NotFoundException;
import com.yudiol.JobSearchPlatformBack.model.Statistic;
import com.yudiol.JobSearchPlatformBack.repository.StatisticRepository;
import com.yudiol.JobSearchPlatformBack.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticRepository statisticRepository;

    @Transactional
    public void save(String userId, Long companyId, Integer status) {
        Statistic statistic = new Statistic();
        statistic.setCompanyId(companyId);
        statistic.setUserId(userId);
        switch (status) {
            case 0:
                break;
            case 1:
                statistic.setHrInterview(LocalDate.now());
                break;
            case 2:
                statistic.setTechInterview(LocalDate.now());
                break;
            case 3:
                statistic.setCaseInterview(LocalDate.now());
                break;
            case 4:
                statistic.setManagerInterview(LocalDate.now());
                break;
            case 5:
                statistic.setOffer(LocalDate.now());
                break;
            case 6:
                statistic.setTests(LocalDate.now());
                break;
            default: {
                throw new NotFoundException("Колонка в таблице", String.valueOf(status));
            }
        }
        statisticRepository.save(statistic);
    }


    @Transactional
    public void update(Long companyId, String userId, Integer status) {
        switch (status) {
            case 0:
                break;
            case 1:
                statisticRepository.setHrInterview(companyId, userId, LocalDate.now());
                break;
            case 2:
                statisticRepository.setTechInterview(companyId, userId, LocalDate.now());
                break;
            case 3:
                statisticRepository.setCaseInterview(companyId, userId, LocalDate.now());
                break;
            case 4:
                statisticRepository.setManagerInterview(companyId, userId, LocalDate.now());
                break;
            case 5:
                statisticRepository.setOffer(companyId, userId, LocalDate.now());
                break;
            case 6:
                statisticRepository.setTests(companyId, userId, LocalDate.now());
                break;
            default: {
                throw new NotFoundException("Колонка в таблице", String.valueOf(status));
            }
        }
    }
}