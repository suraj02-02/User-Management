package com.bridge.skill.usermanagement.dto.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationalDetails {

    private String instituteName;
    private String specialization;
    private String startYear;
    private String endYear;
}
