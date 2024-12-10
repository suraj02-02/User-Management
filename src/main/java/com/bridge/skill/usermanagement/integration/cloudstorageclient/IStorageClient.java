package com.bridge.skill.usermanagement.integration.cloudstorageclient;

import java.io.InputStream;

/**
 * <p>
 *     Interface for cloud storage of documents.
 * </p>
 * @author surajyadav
 */
public interface IStorageClient {

      /**
       * Invoke this to upload this document to S3 storage.
       * @param inputStream
       * @param fileName
       */
      void uploadDocument(final InputStream inputStream , final String fileName);
}
