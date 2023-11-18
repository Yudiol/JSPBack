package com.yudiol.JobSearchPlatformBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    private Contact contact;
    private Summary summary;
    private Skill skill;
    private Education education;
    private Experience experience;
}
