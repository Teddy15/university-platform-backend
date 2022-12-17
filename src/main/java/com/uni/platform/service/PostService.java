package com.uni.platform.service;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import org.springframework.http.ResponseEntity;

public interface PostService {
    public QueryPostDto getAllPosts(int currentPage, int itemsPerPage);

    public PostDto getPostById(Long id);

    public PostDto getPostByTitle(String title);

    public ResponseEntity<String> insertPost(CreatePostDto createPostDto);

    public PostDto updatePost(Long id, CreatePostDto postDto);

    public ResponseEntity<String> deletePostById(Long id);
}
