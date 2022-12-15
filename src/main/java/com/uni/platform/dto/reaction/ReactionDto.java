package com.uni.platform.dto.reaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionDto {
    @NotNull
    private Long id;

    @NotNull
    private String reactedBy;

    @NotNull
    private Boolean isPositive;
}
