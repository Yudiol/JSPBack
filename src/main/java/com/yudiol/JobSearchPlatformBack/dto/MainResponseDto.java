package com.yudiol.JobSearchPlatformBack.dto;

import com.yudiol.JobSearchPlatformBack.model.Link;
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

    private List<Link> links;
    private StatisticResponseDto statistics;
}
