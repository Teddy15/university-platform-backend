package com.uni.platform.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
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

import javax.validation.constraints.NotBlank;
import java.io.File;

@Service
public class AttachmentService {
    private static final String UPLOAD_SUCCESS = "Successfully uploaded a file!";

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
        AmazonClientConfig amazonClientConfig = new AmazonClientConfig(appConfig);
        AmazonS3 amazonS3 = amazonClientConfig.getAmazonS3Client();

        // todo -> get them from the appconfig
        String bucketName = "";
        String fileKey = "";

        byte[] contentAsBytes = fileContent.getBytes();

        return new String();
    }
}
