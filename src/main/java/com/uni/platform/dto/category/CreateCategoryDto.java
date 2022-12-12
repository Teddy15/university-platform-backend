package com.uni.platform.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDto {
    @NotBlank
    private String name;

    //private LocalDateTime registeredAt;

    //private LocalDateTime lastUpdatedAt;
}