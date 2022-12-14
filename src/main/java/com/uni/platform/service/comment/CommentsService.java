package com.uni.platform.service.comment;

import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.comment.CreateCommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentsService {

    CommentDto getCommentsById(Long id);

    List<CommentDto> getAllComments();

    ResponseEntity<String> createComment(CreateCommentDto createCommentDto);

    ResponseEntity<String> deleteComment(Long id);

    ResponseEntity<String> updateComment(CommentDto commentDto, Long id);
}
