package com.bridge.skill.usermanagement.integration.cloudstorageclient;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *  Implementation is to do operations like upload / delete document on <code>S3 storage</code>
 * </p>
 *
 * @author surajyadav
 */
@Service
public class S3StorageImpl implements IStorageClient {

    private AmazonS3 awsS3Client;

    @Value("${documentType}")
    private String[] type;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${secretAccessKey}")
    private String secretAccessKey;

    @PostConstruct
    public void init() {
        // Initialize the AmazonS3 client after properties are injected
        this.awsS3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey))).
                withRegion(Regions.US_EAST_2).build();
    }

    @Override
    public void uploadDocument(InputStream inputStream, String fileName, String bucketName) {
        try {
            if (awsS3Client.doesObjectExist(bucketName, fileName)) {
                deleteFile(bucketName, fileName);
                System.out.println(" Already existing file with file name " + fileName + " is deleted successfully ");
            }
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
            awsS3Client.putObject(putObjectRequest);
            System.out.println("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to S3", e);
        }
    }

    @Override
    public InputStream downloadFile(String fileName, String bucketName) {
        try {
            S3Object s3Object = awsS3Client.getObject(new GetObjectRequest(bucketName, fileName));
            return s3Object.getObjectContent();
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file from S3", e);
        }
    }

    @Override
    public void deleteFile(String fileName, String bucketName) {
        try {
            awsS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
            System.out.println("File deleted successfully: " + fileName);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting file from S3", e);
        }
    }

    @Override
    public void deleteAllDocumentsForPrefix(String prefix, String bucketName) {
        try{
            if (bucketName == null || prefix == null) {
                throw new IllegalArgumentException("Bucket name and prefix must not be null");
            }

            List<String> keysToDelete = fetchAllKeys(bucketName, prefix);

            if (keysToDelete.isEmpty()) {
                System.out.printf("No objects found with prefix '%s' in bucket '%s'%n", prefix, bucketName);
                return;
            }

            for (var batch : partition(keysToDelete)) {
                deleteBatch(bucketName, batch);
            }

            System.out.printf("Deleted all objects with prefix '%s' in bucket '%s'%n", prefix, bucketName);

        }
        catch (Exception e) {
            throw new RuntimeException("Error deleting files from S3", e);
        }
    }

    private List<String> fetchAllKeys(String bucketName, String prefix) {
        ObjectListing objectListing = awsS3Client.listObjects(bucketName, prefix);

        return Stream.iterate(objectListing, ObjectListing::isTruncated,
                        listing -> awsS3Client.listNextBatchOfObjects(listing))
                .flatMap(listing -> listing.getObjectSummaries().stream())
                .map(S3ObjectSummary::getKey)
                .toList();
    }

    private void deleteBatch(String bucketName, List<String> keys) {
        var deleteRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(keys.toArray(new String[0]));
        awsS3Client.deleteObjects(deleteRequest);
        System.out.printf("Deleted %d objects in batch%n", keys.size());
    }

    private static <T> List<List<T>> partition(List<T> list) {
        return Stream.iterate(0, i -> i + 1000)
                .limit((list.size() + 1000 - 1) / 1000)
                .map(i -> list.subList(i, Math.min(i + 1000, list.size())))
                .toList();
    }

}
