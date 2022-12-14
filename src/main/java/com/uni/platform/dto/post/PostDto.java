package com.uni.platform.dto.post;

import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.user.UserPostDto;
import com.uni.platform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    private UserPostDto user;

    private Set<CommentDto> comments;
}
