package com.uni.platform.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
