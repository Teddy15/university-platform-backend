package com.uni.platform.dto.reaction;

import com.uni.platform.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionDto {
    @NotNull
    private Long id;

    @NotNull
    private String reaction;

    @NotNull
    private LocalDateTime reactedOn;

    @NotNull
    private Post post;
}
