package com.uni.platform.controller;

import com.uni.platform.dto.attachment.AttachmentDto;
import com.uni.platform.service.attachment.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/attachments")
public class AttachmentController {
    private final AttachmentServiceImpl attachmentServiceImpl;

    @Autowired
    public AttachmentController(AttachmentServiceImpl attachmentServiceImpl){
        this.attachmentServiceImpl = attachmentServiceImpl;
    }

    @GetMapping("/{fileId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AttachmentDto> downloadFile(@PathVariable Long fileId){
        log.info("downloadFile() called");
        return attachmentServiceImpl.downloadFile(fileId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> uploadFile(@Validated @RequestBody AttachmentDto attachmentDto){
        log.info("uploadFile() called");
        return attachmentServiceImpl.uploadFile(attachmentDto);
    }
}
