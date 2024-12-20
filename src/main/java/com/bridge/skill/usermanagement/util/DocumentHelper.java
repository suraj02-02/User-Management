package com.bridge.skill.usermanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentHelper {

    private static final String FILE_SEPARATOR = "/";

    public static String buildDocumentName(final String userId, final String documentType) {
        return userId + FILE_SEPARATOR + documentType;
    }
    
}
