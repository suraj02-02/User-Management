package com.bridge.skill.usermanagement.dto.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class JobExperienceDetails implements Serializable {

    private String companyName;
    private String designation;
    private String startYear;
    private String endYear;
}
