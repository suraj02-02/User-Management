package com.bridge.skill.usermanagement.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDocument {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String fileName;

    @NotBlank
    private String fileType;

    @NotBlank
    private String bucketKey;

    @CreatedDate
    private LocalDateTime createdOn;

    @LastModifiedDate
    private LocalDateTime updatedOn;

}
