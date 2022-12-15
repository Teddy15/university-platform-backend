package com.uni.platform.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class UserPostComment {
    @EmbeddedId
    private UserPostCommentId id;
}
