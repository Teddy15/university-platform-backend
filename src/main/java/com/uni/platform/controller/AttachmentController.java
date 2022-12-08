package com.uni.platform.controller;

import com.uni.platform.dto.attachment.AttachmentDto;
import com.uni.platform.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService){
        this.attachmentService = attachmentService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@Validated @RequestBody AttachmentDto attachmentDto){
        log.info("uploadFile() called");
        return attachmentService.uploadFile(attachmentDto);
    }
}
