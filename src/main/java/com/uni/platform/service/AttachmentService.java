package com.uni.platform.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.uni.platform.config.AmazonClientConfig;
import com.uni.platform.config.AppConfig;
import com.uni.platform.dto.attachment.AttachmentDto;
import com.uni.platform.entity.Attachment;
import com.uni.platform.repository.AttachmentRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AttachmentService {
    private static final String UPLOAD_SUCCESS = "Successfully uploaded a file!";

    private static final String UPLOAD_KEY = "/posts/%s-%s.%s";

    private final AttachmentRepository attachmentRepository;
    private final AppConfig appConfig;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AppConfig appConfig){
        this.attachmentRepository = attachmentRepository;
        this.appConfig = appConfig;
    }

    public ResponseEntity<AttachmentDto> downloadFile(Long fileId){
        Attachment attachment = attachmentRepository
                .findById(fileId)
                .orElseThrow(() -> new NoSuchElementException("No file found with id: " + fileId));
        String downloadKey = String.format(
                UPLOAD_KEY, attachment.getFileKey(), attachment.getFileName(), attachment.getFileType());
        AttachmentDto result = new AttachmentDto();

        try{
            AmazonClientConfig amazonClientConfig = new AmazonClientConfig(appConfig);
            AmazonS3 amazonS3 = amazonClientConfig.getAmazonS3Client();

            S3Object obj = amazonS3.getObject(
                    appConfig.getAmazonS3Config().getBucketName(), downloadKey);

            byte[] content = obj.getObjectContent().readAllBytes();

            result.setFileName(attachment.getFileName());
            result.setFileType(attachment.getFileType());
            result.setFileContent(new String(content));
        }
        catch(AmazonServiceException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> uploadFile(AttachmentDto attachmentDto){
        String fileKey = uploadFileToAmazon(attachmentDto).getBody();

        Attachment attachment = new Attachment();
        attachment.setFileKey(fileKey);
        attachment.setFileName(attachmentDto.getFileName());
        attachment.setFileType(attachmentDto.getFileType());

        attachmentRepository.save(attachment);

        return new ResponseEntity<>(UPLOAD_SUCCESS, HttpStatus.OK);
    }

    private ResponseEntity<String> uploadFileToAmazon(AttachmentDto attachmentDto) {
        String fileKey = constructPostAttachmentFileKey(
                attachmentDto.getFileName(), attachmentDto.getFileType());
        PutObjectResult result;

        try{
            File tempFile = new File(fileKey);
            FileUtils.writeByteArrayToFile(tempFile, attachmentDto.getFileContent().getBytes());

            AmazonClientConfig amazonClientConfig = new AmazonClientConfig(appConfig);
            AmazonS3 amazonS3 = amazonClientConfig.getAmazonS3Client();

            result = amazonS3.putObject(
                    appConfig.getAmazonS3Config().getBucketName(), fileKey, tempFile);

            if(result == null){
                throw new AmazonServiceException("Problem occurred while executing putObject()");
            }

            if(!tempFile.delete()){
                throw new IOException("Problem occurred while deleting temporary file with fileKey: " + fileKey);
            }
        }
        catch(AmazonServiceException | IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(fileKey, HttpStatus.OK);
    }

    private String constructPostAttachmentFileKey(String fileName, String fileType){
        return String.format(
                UPLOAD_KEY, fileName, UUID.randomUUID(), fileType);
    }
}
