package com.uni.platform.Controllers;

import com.uni.platform.dto.CommentDto;
import com.uni.platform.Services.CommentsService;
import com.uni.platform.dto.CreateCommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllComments() {
        log.info("getAllComments() called");
        List<CommentDto> comments = commentsService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        log.info("getCommentById() called");
        CommentDto comments = commentsService.getCommentsById(id);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        log.info("updateComment() called");
        return commentsService.updateComment(commentDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        log.info("deleteComment() called");
        return commentsService.deleteComment(id);
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CreateCommentDto createCommentDto) {
        log.info("createComment() called");
        return commentsService.createComment(createCommentDto);
    }
}
