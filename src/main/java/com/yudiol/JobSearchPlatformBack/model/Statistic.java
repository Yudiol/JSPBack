package com.yudiol.JobSearchPlatformBack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "hr_interview")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hrInterview;

    @Column(name = "tests")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tests;

    @Column(name = "tech_interview")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate techInterview;

    @Column(name = "case_interview")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate caseInterview;

    @Column(name = "manager_interview")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate managerInterview;

    @Column(name = "offer")
    private Integer offer;
}
