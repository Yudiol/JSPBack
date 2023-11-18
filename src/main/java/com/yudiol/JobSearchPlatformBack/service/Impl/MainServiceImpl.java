package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.MainResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.QuantityInterview;
import com.yudiol.JobSearchPlatformBack.dto.StatisticResponseDto;
import com.yudiol.JobSearchPlatformBack.model.Link;
import com.yudiol.JobSearchPlatformBack.repository.ResponseRepository;
import com.yudiol.JobSearchPlatformBack.repository.StatisticRepository;
import com.yudiol.JobSearchPlatformBack.repository.UserLinkRepository;
import com.yudiol.JobSearchPlatformBack.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainServiceImpl implements MainService {
    private final UserLinkRepository userLinkRepository;
    private final StatisticRepository statisticRepository;
    private final ResponseRepository responseRepository;


    public MainResponseDto getMainResponseDto(String id, Integer month, Integer year) throws SQLException {

        List<Link> userLinks = findAllLinksByUserId(id);
        StatisticResponseDto statisticResponseDto = createStatisticResponseDto(id, month, year);

        MainResponseDto mainResponseDto = new MainResponseDto();
        mainResponseDto.setLinks(userLinks);
        mainResponseDto.setStatistics(statisticResponseDto);
        return mainResponseDto;
    }


    private Long calculatePercentage(Long total, Long quantity) {
        if (total == null) {
            return 0L;
        }
        return (quantity * 100) / total;
    }

    private Long findTotalQuantityTestByUserId(String id, Integer month, Integer year) {
        return statisticRepository.findTotalQuantityTestByUserId(id, month, year);
    }

    private Long findTotalQuantityByUserId(String id, Integer month, Integer year) {
        return responseRepository.findTotalQuantityByUserId(id, month, year);
    }

    private List<Link> findAllLinksByUserId(String id) {
        return userLinkRepository.findAllByUserId(id).orElse(null);
    }


    private List<QuantityInterview> findQuantityHrInterview(String id, Integer month, Integer year) {
        return statisticRepository.findAllByMonthAndYear(id, month, year);
    }

    private StatisticResponseDto createStatisticResponseDto(String id, Integer month, Integer year) {

        List<QuantityInterview> listHrInterviews = findQuantityHrInterview(id, month, year);

        Long quantityHrInterview = listHrInterviews.stream().map(QuantityInterview::getCount).reduce(Long::sum).orElse(null);
        Long quantityTests = findTotalQuantityTestByUserId(id, month, year);

        Long total = findTotalQuantityByUserId(id, month, year);

        Long percentTest = calculatePercentage(total, quantityTests);
        Long percentHrInterview = calculatePercentage(total, quantityHrInterview);

        StatisticResponseDto statisticResponseDto = new StatisticResponseDto();
        statisticResponseDto.setListInterview(listHrInterviews);
        statisticResponseDto.setPercentageOfHrInterview(percentHrInterview);
        statisticResponseDto.setPercentageOfTests(percentTest);
        statisticResponseDto.setTotal(total);

        return statisticResponseDto;
    }
}
