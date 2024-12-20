package com.bridge.skill.usermanagement.service.intf;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * <code>UploadService provides an interface to upload document for a user</code>
 * </p>
 * <p>
 * It will act as a generic service to upload documents of a user.
 * </p>
 *
 * @author surajyadav
 */
public interface IUploadService {

    /**
     * Invoke this method to upload document for a user to the configured cloud storage
     *
     * @param file
     * @param newFileName
     * @return message
     */
    String uploadDocument(final MultipartFile file, final String newFileName);

    String deleteAllDocumentsForPrefix(final String userId);

}
