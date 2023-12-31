package com.yudiol.JobSearchPlatformBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String linkedin;
    private String github;
    private String behance;
    private String site;
    private String country;
}
