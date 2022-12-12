package com.uni.platform.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    @NotBlank
    private String fileName;

    @NotBlank
    private Long postId;

    @NotBlank
    private String fileType;

    @NotBlank
    private String fileContent;
}