package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.ResumeBytesRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResumePdfResponseDto;
import com.yudiol.JobSearchPlatformBack.service.UploadedPdfResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/pdf")
public class UploadedPdfResumeController {
    private final UploadedPdfResumeService uploadedPdfResumeService;


    @GetMapping("/uploaded/{userId}")
    @Operation(summary = "Получить резюме которое загрузил пользователь по userId ( массив байт )")
    public ResumePdfResponseDto getUploadedResume(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId) {
        return uploadedPdfResumeService.findByUserId(userId);
    }

    @PostMapping("/uploadedPdf/{userId}")
    @Operation(summary = "Сохранить резюме которое загрузил пользователь по userId ( PDF передача form-data )")
    public void savePdfResume(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId, @RequestParam("file") MultipartFile file) throws IOException {
        uploadedPdfResumeService.savePdf(userId, file);
    }

    @PostMapping("/uploadedBytes/{userId}")
    @Operation(summary = "Сохранить резюме которое загрузил пользователь по userId ( PDF передача bytes ) ")
    public void saveBytesResume(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId, @RequestBody ResumeBytesRequestDto requestDto) {
        uploadedPdfResumeService.saveBytes(userId, requestDto.getBytes());
    }

    @DeleteMapping("/uploaded/{userId}")
    @Operation(summary = "Удалить резюме(PDF) которое пользователь загрузил по userId")
    public void deleteUploadedResume(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId) {
        uploadedPdfResumeService.deleteByUserId(userId);
    }
}
