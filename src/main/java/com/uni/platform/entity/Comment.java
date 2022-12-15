package com.uni.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "comment")
@Table(name = "comment", schema="uni_platform")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @Column(columnDefinition = "SERIAL")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String content;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime last_updated_at;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName = "id", nullable=false)
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
