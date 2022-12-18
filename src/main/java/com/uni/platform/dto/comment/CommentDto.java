package com.uni.platform.dto.comment;

import com.uni.platform.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull
    private Long id;

    @NotBlank
    private String content;

    private LocalDateTime created_at;

    private LocalDateTime last_updated_at;

    private UserInfoDto user;
}
