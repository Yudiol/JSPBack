package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.service.GeneratedPdfResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class GeneratedPdfResumeController {

    private final GeneratedPdfResumeService generatedPdfResumeService;

    @GetMapping("/generated/{userId}")
    @Operation(summary = "Получить PDF из резюме пользователя ( массив байт )")
    public ResumePdfResponseDto create(@PathVariable @Parameter(description = "Идентификатор пользователя") String userId) {
        return generatedPdfResumeService.createPdf(userId);
    }
}
