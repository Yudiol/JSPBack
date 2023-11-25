package com.yudiol.JobSearchPlatformBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Institution {
    private String name;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
