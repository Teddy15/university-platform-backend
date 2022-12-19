package com.uni.platform.controller;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import com.uni.platform.dto.reaction.CreateReactionDto;
import com.uni.platform.dto.reaction.ReactionDto;
import com.uni.platform.service.PostService;
import com.uni.platform.service.ReactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/posts")
public class PostController {
    private final PostService postService;

    private final ReactionService reactionService;

    @Autowired
    public PostController(PostService postService, ReactionService reactionService) {
        this.postService = postService;
        this.reactionService = reactionService;
    }

    @GetMapping
    public QueryPostDto getAllPosts(@RequestParam(defaultValue = "1") int currentPage,
                                    @RequestParam(defaultValue = "10") int perPage) {

        log.info("getAllPosts() called");
        return postService.getAllPosts(currentPage, perPage);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        log.info("getPostById() called");
        return postService.getPostById(postId);
    }

    @PostMapping
    public ResponseEntity<String> insertPost(@Validated @RequestBody CreatePostDto createPostDto) {
        log.info("insertPost() called");
        return postService.insertPost(createPostDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        log.info("updatePost() called");
        return postService.updatePost(postId, postDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        log.info("deletePost() called");
        return postService.deletePostById(postId);
    }

    @PostMapping("/{postId}/reaction")
    public ReactionDto insertReaction(@PathVariable Long postId, @Validated @RequestBody CreateReactionDto createReactionDto) {
        log.info("insertReaction() called");
        return reactionService.insertReactionDto(createReactionDto);
    }

    @DeleteMapping("/{reactionId}/reaction")
    public ResponseEntity<String> deleteReaction(@PathVariable Long reactionId) {
        log.info("deleteReaction() called");
        return reactionService.deleteReactionById(reactionId);
    }
}