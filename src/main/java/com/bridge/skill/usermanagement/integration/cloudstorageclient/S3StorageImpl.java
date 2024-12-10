package com.bridge.skill.usermanagement.integration.cloudstorageclient;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 *  Implementation is to do operations like upload / delete document on <code>S3 storage</code>
 * </p>
 *
 * @author surajyadav
 */
@Service
public class S3StorageImpl implements IStorageClient {

    private final AmazonS3 awsS3Client;

    public S3StorageImpl() {
        /**
         *  TODO read credentials from properties
         */
        this.awsS3Client = AmazonS3Client.builder()
                .withCredentials((AWSCredentialsProvider) new BasicAWSCredentials(null , null))
                .withRegion(Regions.DEFAULT_REGION)
                .build();
    }

    @Override
    public void uploadDocument(InputStream inputStream, String fileName) {
        /**
         * TODO
         *  handle the exceptions
         *  configure bucket
         *  configure security
         *  configure metadata
         */
        this.awsS3Client.putObject(new PutObjectRequest("bucket-Name", fileName, inputStream, null));
    }
}
