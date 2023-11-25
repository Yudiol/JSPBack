package com.yudiol.JobSearchPlatformBack.dto;

import com.yudiol.JobSearchPlatformBack.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "resume")
public class ResumeDto {
    String userId;
    Resume resume;
}
