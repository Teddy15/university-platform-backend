package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "reaction")
@Table(name = "reaction", schema = "uni_platform")
@Data
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String reaction;

    @NotNull
    private LocalDateTime reactedOn;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Post post;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public LocalDateTime getReactedOn() {
        return reactedOn;
    }

    public void setReactedOn(LocalDateTime reactedOn) {
        this.reactedOn = reactedOn;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
