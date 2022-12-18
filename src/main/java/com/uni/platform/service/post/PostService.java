package com.uni.platform.service.post;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import org.springframework.http.ResponseEntity;

public interface PostService {
    QueryPostDto getAllPosts(int currentPage, int itemsPerPage);

    PostDto getPostById(Long id);

    PostDto getPostByTitle(String title);

    ResponseEntity<String> insertPost(CreatePostDto createPostDto);

    PostDto updatePost(Long id, CreatePostDto postDto);

    ResponseEntity<String> deletePostById(Long id);
}
