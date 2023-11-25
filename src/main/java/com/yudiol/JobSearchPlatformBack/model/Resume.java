package com.yudiol.JobSearchPlatformBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "resumes")
public class Resume {
    private Contact contact;
    private Summary summary;
    private Skill skill;
    private Education education;
    private Experience experience;
}
