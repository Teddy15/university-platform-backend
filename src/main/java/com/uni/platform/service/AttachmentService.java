package com.uni.platform.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.uni.platform.config.AmazonClientConfig;
import com.uni.platform.config.AppConfig;
import com.uni.platform.dto.attachment.AttachmentDto;
import com.uni.platform.entity.Attachment;
import com.uni.platform.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AttachmentService {
    private static final String UPLOAD_SUCCESS = "Successfully uploaded a file!";
    private static final String UPLOAD_KEY = "%{key}/{filename}.{extension}";

    private final AttachmentRepository attachmentRepository;
    private final AppConfig appConfig;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AppConfig appConfig){
        this.attachmentRepository = attachmentRepository;
        this.appConfig = appConfig;
    }

    public ResponseEntity<String> uploadFile(AttachmentDto attachmentDto){
        String fileKey = uploadFileToAmazon(
                attachmentDto.getFileName(),
                attachmentDto.getFileType(),
                attachmentDto.getFileContent());

        Attachment attachment = new Attachment();
        attachment.setFileKey(fileKey);
        attachment.setFileName(attachmentDto.getFileName());
        attachment.setFileType(attachmentDto.getFileType());

        attachmentRepository.save(attachment);

        return new ResponseEntity<>(UPLOAD_SUCCESS, HttpStatus.OK);
    }

    private String uploadFileToAmazon(String fileName, String fileType, String fileContent){
        String key = java.util.UUID.randomUUID().toString();
        String uploadKey = String.format(UPLOAD_KEY, key, fileName, fileType);

        File tempFile = new File(key);
        String uploadedFileKey = "";

        // todo -> different fileKey = tempFileName

        try{
            byte[] contentAsBytes = fileContent.getBytes();

            try {
                try (FileOutputStream fileStream = new FileOutputStream(key)) {
                    fileStream.write(contentAsBytes);
                }
            }
            catch(Exception e) {
            }

            AmazonClientConfig amazonClientConfig = new AmazonClientConfig(appConfig);
            AmazonS3 amazonS3 = amazonClientConfig.getAmazonS3Client();

            PutObjectRequest request = new PutObjectRequest(
                    appConfig.getAmazonS3Config().getBucketName(), uploadKey, tempFile);

            PutObjectResult putObjectResult = amazonS3.putObject(request);

            uploadedFileKey = request.getKey();
        }
        finally{
            tempFile.delete();
        }

        return uploadedFileKey;
    }
}
