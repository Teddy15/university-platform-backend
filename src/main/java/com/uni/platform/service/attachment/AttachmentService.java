package com.uni.platform.service.attachment;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.uni.platform.config.AmazonClientConfig;
import com.uni.platform.dto.attachment.AttachmentDto;
import com.uni.platform.entity.Attachment;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;

public interface AttachmentService {
    public ResponseEntity<AttachmentDto> downloadFile(Long fileId);

    public ResponseEntity<String> uploadFile(AttachmentDto attachmentDto);
}
