package com.yudiol.JobSearchPlatformBack.controller;

import com.yudiol.JobSearchPlatformBack.model.Resume;
import com.yudiol.JobSearchPlatformBack.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/{userId}")
    @Operation(summary = "Сохранить резюме пользователя по userId")
    public void save(@PathVariable("userId") String userId, @RequestBody Resume resume) {
        resumeService.save(userId, resume);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получить резюме пользователя по userId")
    public Resume getByUserId(@PathVariable("userId") String userId) {
        return resumeService.findByUserId(userId);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Удалить резюме пользователя по userId")
    public void deleteByUserId(@PathVariable("userId") String userId) {
        resumeService.deleteByUserId(userId);
    }

}
