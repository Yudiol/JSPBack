package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.AdviceResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.MainResponseDto;
import com.yudiol.JobSearchPlatformBack.service.AdviceService;
import com.yudiol.JobSearchPlatformBack.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDate;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    private final AdviceService adviceService;

    @GetMapping("/{id}")
    public MainResponseDto index(@PathVariable("id") String id) throws SQLException {
        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();
        return mainService.getMainResponseDto(id, month, year);
    }

    @GetMapping("/advice/{id}")
    public AdviceResponseDto getAdvice(@PathVariable("id") Long id) {
        return adviceService.getAdvice(id);
    }
}
