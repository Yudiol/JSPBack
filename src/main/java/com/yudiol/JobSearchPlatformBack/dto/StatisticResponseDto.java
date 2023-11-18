package com.yudiol.JobSearchPlatformBack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponseDto {
    private List<QuantityInterview> listInterview;
    private Long total;
    private Long percentageOfHrInterview;
    private Long percentageOfTests;
}
