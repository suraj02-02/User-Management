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
      void uploadDocument(final InputStream inputStream , final String fileName, String bucketName);

      // Download file from S3
      InputStream downloadFile(String fileName, String bucketName);

      // Delete file from S3
      void deleteFile(String fileName, String bucketName);

      /**
       * Delete all the documents in the bucket for the given prefix.
       * @param prefix
       * @param bucketName
       */
      void deleteAllDocumentsForPrefix(String prefix, String bucketName);
}
