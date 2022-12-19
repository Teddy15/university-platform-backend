package com.uni.platform.dto.comment;

import com.uni.platform.dto.attachment.AttachmentInfoDto;
import com.uni.platform.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    private UserInfoDto user;

    private AttachmentInfoDto attachment;
}
