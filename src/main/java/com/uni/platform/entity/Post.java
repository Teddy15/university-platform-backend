package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "post")
@Table(name = "post", schema = "uni_platform")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime lastUpdatedAt;

    @OneToMany(mappedBy = "reaction")
    private List<Reaction> reactions;
}
