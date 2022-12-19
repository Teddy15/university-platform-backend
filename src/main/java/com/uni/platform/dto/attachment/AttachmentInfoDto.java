package com.uni.platform.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentInfoDto {
    private Long id;

    @NotBlank
    private String attachmentName;

    @NotBlank
    private String attachmentType;
}
