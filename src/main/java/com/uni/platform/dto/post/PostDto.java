package com.uni.platform.dto.post;

import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

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

    private UserInfoDto user;

    private CategoryDto category;

    private List<CommentDto> comments;
}
