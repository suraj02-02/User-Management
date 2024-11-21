package com.bridge.skill.usermanagement.dto.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EducationalDetails implements Serializable {

    private String instituteName;
    private String specialization;
    private String startYear;
    private String endYear;
}
