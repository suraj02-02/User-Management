package com.bridge.skill.usermanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
public class GenericExceptionResponse implements Serializable {

    private int statusCode;
    private String exceptionMessage;
    private LocalDateTime timestamp;
}
