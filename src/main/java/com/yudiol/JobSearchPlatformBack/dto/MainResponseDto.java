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
public class MainResponseDto {

    private List<LinkDto> links;
    private StatisticResponseDto statistics;
}
