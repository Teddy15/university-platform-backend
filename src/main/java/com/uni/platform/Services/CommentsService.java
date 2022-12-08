package com.uni.platform.Services;

import com.uni.platform.Enities.Comment;
import com.uni.platform.Repositories.CommentRepository;
import com.uni.platform.RequestBodies.CommentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService implements ICommentsService {

    private CommentRepository commentRepository;

    public CommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> GetCommentsById(Integer id) {
        Optional<Comment> comment = commentRepository.findCommentById(id);
        return comment;
    }

    @Override
    public List<Comment> GetAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    @Override
    public boolean CreateComment(CommentRequest commentRequest, Integer id) {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(id, commentRequest.getTitle(),
                commentRequest.getContent(), createdAt);

        if(comment != null) {
            comment.setLast_updated_at(createdAt);
            commentRepository.save(comment);
            return true;
        }

        return false;
    }

    @Override
    public boolean DeleteComment(Integer id) {

        Comment comment = commentRepository.findCommentById(id).get();

        if(comment != null) {
            commentRepository.delete(comment);
            return true;
        }

        return false;
    }

    @Override
    public void UpdateComment(CommentRequest commentRequest, Integer id) {
        Optional<Comment> comment = commentRepository.findCommentById(id).map(
                response -> {
                    response.setContent(commentRequest.getContent());
                    response.setTitle(commentRequest.getTitle());
                    return commentRepository.save(response);
                }
        );
    }

}
