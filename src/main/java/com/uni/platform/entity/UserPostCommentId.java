package com.uni.platform.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserPostCommentId implements Serializable {
    //todo -> potential bug

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
