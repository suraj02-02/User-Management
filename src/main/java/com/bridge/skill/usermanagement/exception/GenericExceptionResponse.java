package com.bridge.skill.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericExceptionResponse {

    private int statusCode;
    private String exceptionMessage;
    private LocalDateTime timestamp;
}
