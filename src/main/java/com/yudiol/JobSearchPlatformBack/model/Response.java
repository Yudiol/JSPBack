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

import java.time.LocalDate;

@Entity
@Table(name = "responses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "response_date")
    private LocalDate responseDate;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "position")
    private String position;

    @Column(name = "contact")
    private String contact;

    @Column(name = "status")
    private int status;

    @Column(name = "comments")
    private String comments;
}
