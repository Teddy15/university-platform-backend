package com.uni.platform.controller;

import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.service.CommentsService;
import com.uni.platform.dto.comment.CreateCommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/uni-platform/comments")
@CrossOrigin(origins = "*", maxAge = 30)
public class CommentController {

    private CommentsService commentsService;

    public CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllComments() {
        log.info("getAllComments() called");
        List<CommentDto> comments = commentsService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        log.info("getCommentById() called");
        CommentDto comments = commentsService.getCommentsById(id);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        log.info("updateComment() called");
        return commentsService.updateComment(commentDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        log.info("deleteComment() called");
        return commentsService.deleteComment(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> createComment(@RequestBody CreateCommentDto createCommentDto) {
        log.info("createComment() called");
        return commentsService.createComment(createCommentDto);
    }
}
