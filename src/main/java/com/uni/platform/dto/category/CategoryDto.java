package com.uni.platform.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
