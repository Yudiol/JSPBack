package com.yudiol.JobSearchPlatformBack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResumePdfResponseDto {

    private byte[] bytes;
}
