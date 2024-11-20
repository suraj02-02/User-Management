package com.bridge.skill.usermanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class GenericExceptionResponse {

    private int statusCode;
    private String exceptionMessage;
    private LocalDateTime timestamp;
}
