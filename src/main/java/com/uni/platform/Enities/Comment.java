package com.uni.platform.Enities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity(name = "comment")
@Data
@Table(name = "comment")
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
}
