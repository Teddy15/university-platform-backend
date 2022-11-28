package com.uni.platform.service;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import com.uni.platform.entity.Post;
import com.uni.platform.mapper.PostMapper;
import com.uni.platform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PostService {
    private static final String CREATE_SUCCESS = "Successfully created a post!";
    private static final String UPDATE_SUCCESS = "Successfully updated the post!";
    private static final String DELETE_SUCCESS = "Successfully deleted the post!";

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public QueryPostDto getAllPosts(int currentPage, int itemsPerPage){
        //todo -> validate arguments

        Pageable pageable = PageRequest.of(currentPage - 1, itemsPerPage);
        Page<Post> postPage = postRepository.filterPostPages(pageable);

        QueryPostDto result = new QueryPostDto();

        result.setPosts(postMapper.postEntityToPostDto(postPage.toList()));
        result.setCurrentPage(currentPage);
        result.setItemsPerPage(itemsPerPage);
        result.setTotalPages(postPage.getTotalPages());
        result.setTotalElements(postPage.getTotalElements());

        return result;
    }

    public PostDto getPostById(Long id){
        return postMapper
                .postEntityToPostDto(
                        postRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("No post found with id: " + id)));
    }

    public PostDto getPostByTitle(String title){
        return postMapper
                .postEntityToPostDto(
                        postRepository.findByTitle(title)
                                .orElseThrow(() -> new NoSuchElementException("No post found with title: " + title)));
    }

    public ResponseEntity<String> insertPost(CreatePostDto createPostDto){
        Post post = postMapper.createPostDtoToPostEntity(createPostDto);
        post.setCreatedAt(LocalDateTime.now());
        post.setLastUpdatedAt(post.getCreatedAt());

        postRepository.save(post);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> updatePost(Long id, PostDto postDto){
        getPostById(id);

        Post post = postMapper.postDtoToPostEntity(postDto);
        post.setLastUpdatedAt(LocalDateTime.now());
        postRepository.save(post);

        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> deletePostById(Long id){
        getPostById(id);

        postRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
