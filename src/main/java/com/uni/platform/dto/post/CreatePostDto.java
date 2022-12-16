package com.uni.platform.dto.post;

import com.uni.platform.dto.category.CategoryInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long categoryId;
}
