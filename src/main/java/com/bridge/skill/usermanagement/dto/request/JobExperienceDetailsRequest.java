package com.bridge.skill.usermanagement.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Valid
public class JobExperienceDetailsRequest {

    @NotBlank(message = "companyName is Invalid")
    private String companyName;

    @NotBlank(message = "designation is Invalid")
    private String designation;

    private boolean isCurrentJob;

    @NotBlank(message = "startYear is Invalid")
    private String startYear;

    @NotBlank(message = "endYear is Invalid")
    private String endYear;

}
