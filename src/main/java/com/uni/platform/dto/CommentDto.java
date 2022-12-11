package com.uni.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull
    private Long id;

    @NotBlank
    private String content;

    private Timestamp created_at;

    private Timestamp last_updated_at;
}
