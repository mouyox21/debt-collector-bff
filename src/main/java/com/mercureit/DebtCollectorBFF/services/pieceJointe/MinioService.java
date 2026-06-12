package com.mercureit.DebtCollectorBFF.services.pieceJointe;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Service
public class MinioService {

    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;




    public MinioService(
            @Value("${minio.url}") String url,
            @Value("${minio.access.key}") String accessKey,
            @Value("${minio.secret.key}") String secretKey)
    {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

    public String uploadFile(MultipartFile file, String folderPath, String fileName) throws Exception {
        InputStream inputStream = file.getInputStream();
        String objectName = folderPath + "/" + fileName;
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        return objectName;
    }


    public String getFile(String objectName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(60 * 60)
                        .build()
        );
    }

    public void deleteFile(String objectName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }
    public void uploadFileForBackup(String fileName, String content) throws Exception {
        try (InputStream inputStream = new ByteArrayInputStream(content.getBytes())) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("segmentation")
                            .object(fileName)
                            .stream(inputStream, inputStream.available(), -1)
                            .build());
            System.out.println("Backup uploaded successfully");
        } catch (MinioException e) {
            throw new RuntimeException("Error uploading file to MinIO", e);
        }
    }

}
