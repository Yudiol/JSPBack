package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.dto.ResponseTableListResponseDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableRequestDto;
import com.yudiol.JobSearchPlatformBack.dto.ResponseTableResponseDto;
import com.yudiol.JobSearchPlatformBack.service.ResponseTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/responseTable")
@ResponseStatus(HttpStatus.OK)
public class ResponseTableController {

    private final ResponseTableService responseTableService;

    @PostMapping("/{userId}")
    @Operation(summary = "Сохранить отклик по вакансии по userId")
    public void save(@PathVariable @Parameter(description = "Идентификатор пользователя") String userId, @RequestBody ResponseTableRequestDto response) {
        responseTableService.save(userId, response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получить все отклики по вакансиям по userId")
    public ResponseTableListResponseDto findAll(@PathVariable @Parameter(description = "Идентификатор пользователя") String userId) {
        return responseTableService.findAll(userId);
    }

    @DeleteMapping("/{userId}/{id}")
    @Operation(summary = "Удалить отклик по вакансии по userId и id отклика")
    public void delete(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId,
                       @PathVariable("id") @Parameter(description = "Идентификатор отклика по вакансии") Long id) {
        responseTableService.deleteByIdAndUserId(userId, id);
    }

    @PatchMapping("/{userId}")
    @Operation(summary = "Изменить отклик по вакансии по userId")
    public void update(@PathVariable("userId") @Parameter(description = "Идентификатор пользователя") String userId, @RequestBody ResponseTableResponseDto response) {
        responseTableService.update(userId, response);
    }
}
