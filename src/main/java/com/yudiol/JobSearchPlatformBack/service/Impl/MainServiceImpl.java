package com.yudiol.JobSearchPlatformBack.service.Impl;

import com.yudiol.JobSearchPlatformBack.dto.LinkDto;
import com.yudiol.JobSearchPlatformBack.dto.MainResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.QuantityResponses;
import com.yudiol.JobSearchPlatformBack.dto.StatisticResponseDto;
import com.yudiol.JobSearchPlatformBack.mapper.Mapper;
import com.yudiol.JobSearchPlatformBack.repository.ResponseTableRepository;
import com.yudiol.JobSearchPlatformBack.repository.StatisticRepository;
import com.yudiol.JobSearchPlatformBack.repository.UserLinkRepository;
import com.yudiol.JobSearchPlatformBack.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainServiceImpl implements MainService {
    private final UserLinkRepository userLinkRepository;
    private final StatisticRepository statisticRepository;
    private final ResponseTableRepository responseTableRepository;
    private final Mapper mapper;


    public MainResponseDto getMainResponseDto(String id, Integer month, Integer year) throws SQLException {

        List<LinkDto> userLinks = findAllLinksByUserId(id);
        StatisticResponseDto statisticResponseDto = createStatisticResponseDto(id, month, year);

        MainResponseDto mainResponseDto = new MainResponseDto();
        mainResponseDto.setLinks(userLinks);
        mainResponseDto.setStatistics(statisticResponseDto);
        return mainResponseDto;
    }


    private Long calculatePercentage(Long total, Long quantity) {
        if (total == null || total == 0) {
            return 0L;
        }
        return (quantity * 100) / total;
    }

    private Long findTotalQuantityTestByUserId(String id, Integer month, Integer year) {
        return statisticRepository.findTotalQuantityTestByUserId(id, month, year);
    }

    private Long findTotalQuantityHrInterviewByUserId(String id, Integer month, Integer year) {
        return statisticRepository.findTotalQuantityHrInterviewByUserId(id, month, year);
    }

    private Long findTotalQuantityByUserId(String id, Integer month, Integer year) {
        return responseTableRepository.findTotalQuantityByUserId(id, month, year);
    }

    private List<LinkDto> findAllLinksByUserId(String id) {
        return userLinkRepository.findAllByUserId(id)
                .map(link -> link.stream()
                        .map(mapper::toLinkDto)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private List<QuantityResponses> findQuantityResponses(String id, Integer month, Integer year) {
        return responseTableRepository.findAllByMonthAndYear(id, month, year);
    }

    private StatisticResponseDto createStatisticResponseDto(String id, Integer month, Integer year) {

        List<QuantityResponses> listResponses = findQuantityResponses(id, month, year);


        Long quantityHrInterview = findTotalQuantityHrInterviewByUserId(id, month, year);

        Long quantityTests = findTotalQuantityTestByUserId(id, month, year);

        Long total = findTotalQuantityByUserId(id, month, year);

        Long percentTest = calculatePercentage(total, quantityTests);
        Long percentHrInterview = calculatePercentage(total, quantityHrInterview);

        StatisticResponseDto statisticResponseDto = new StatisticResponseDto();
        statisticResponseDto.setListResponses(listResponses);
        statisticResponseDto.setPercentageOfHrInterview(percentHrInterview);
        statisticResponseDto.setPercentageOfTests(percentTest);
        statisticResponseDto.setTotal(total);

        return statisticResponseDto;
    }
}
