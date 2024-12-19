package com.bridge.skill.usermanagement.util;

public class DocumentHelper {

    public static String buildDocumentName(String userId, String documentType) {
        return userId + "/" + documentType;
    }

}
