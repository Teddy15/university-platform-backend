package com.uni.platform.Services;

import com.uni.platform.Enities.Comment;
import com.uni.platform.RequestBodies.CommentRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ICommentsService {

    public Optional<Comment> GetCommentsById(Integer id);

    public List<Comment> GetAllComments();

    public boolean CreateComment(CommentRequest commentRequest, Integer id);

    public boolean DeleteComment(Integer id);

    public void UpdateComment(CommentRequest commentRequest, Integer id);
}
