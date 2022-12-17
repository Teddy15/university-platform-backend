package com.uni.platform.controller;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import com.uni.platform.service.PostService;
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

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
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
    public ResponseEntity<String> insertPost(@Validated @RequestBody CreatePostDto createPostDto){
        log.info("insertPost() called");
        return postService.insertPost(createPostDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto){
        log.info("updatePost() called");
        return postService.updatePost(postId, postDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        log.info("deletePost() called");
        return postService.deletePostById(postId);
    }
}