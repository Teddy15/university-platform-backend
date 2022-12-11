package com.uni.platform.Services;

import com.uni.platform.dto.CommentDto;
import com.uni.platform.dto.CreateCommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentsService {

    CommentDto getCommentsById(Long id);

    List<CommentDto> getAllComments();

    ResponseEntity<String> createComment(CreateCommentDto createCommentDto);

    ResponseEntity<String> deleteComment(Long id);

    ResponseEntity<String> updateComment(CommentDto commentDto, Long id);
}
