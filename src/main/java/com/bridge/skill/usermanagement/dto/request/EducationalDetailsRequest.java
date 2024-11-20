package com.bridge.skill.usermanagement.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Valid
public class EducationalDetailsRequest {

    @NotBlank(message = "Institute name is Invalid")
    private String instituteName;

    @NotBlank(message = "Specialization provided is invalid")
    private String specialization;

    @NotBlank(message = "Invalid startYear")
    private String startYear;

    @NotBlank(message = "Invalid endYear")
    private String endYear;
}
