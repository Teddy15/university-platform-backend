package com.uni.platform.Controllers;

import com.uni.platform.Enities.Comment;
import com.uni.platform.RequestBodies.CommentRequest;
import com.uni.platform.Services.CommentsService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uni-platform")
@CrossOrigin(origins = "*", maxAge = 30)
public class CommentController {

    private CommentsService commentsService;

    public CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comments")
    public ResponseEntity<?> GetAllComments() {
        List<Comment> comments = commentsService.GetAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> GetCommentById(@PathVariable Integer id) {
        Optional<Comment> comments = commentsService.GetCommentsById(id);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> UpdateComment(@RequestBody CommentRequest commentRequest,  @PathVariable Integer id) {
        commentsService.UpdateComment(commentRequest, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> DeleteComment(@PathVariable Integer id) {
        boolean result = commentsService.DeleteComment(id);
        if(result == true) {
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> CreateComment(@RequestBody CommentRequest commentRequest, @PathVariable Integer id) {
        boolean result = commentsService.CreateComment(commentRequest, id);
        if(result == true) {
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
