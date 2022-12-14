package com.uni.platform.service;

import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.post.QueryPostDto;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.entity.Post;
import com.uni.platform.mapper.PostMapper;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.repository.PostRepository;
import com.uni.platform.util.SecurityUtils;
import com.uni.platform.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {
    private static final String CREATE_SUCCESS = "Successfully created a post!";
    private static final String DELETE_SUCCESS = "Successfully deleted the post!";

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper,
                       UserService userService, UserMapper userMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userService = userService;
        this.userMapper = userMapper;
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

    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new NoSuchElementException("No post found with id: " + id);
        }

        return postMapper.postEntityToPostDto(post.get());
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

        post.setUser(userMapper.userDtoToUserEntity(userService.getUserByUsername(
                SecurityUtils.getUserDetails().getUsername()
        )));

        postRepository.save(post);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        UserDto currentUser = userService.getUserByUsername(SecurityUtils.getUserDetails().getUsername());
        PostDto currentPost = getPostById(id);

        if (!currentUser.getRole().equals(UserRole.ROLE_ADMIN)
                && !currentPost.getUser().getId().equals(currentUser.getId())) {
            throw new BadCredentialsException("You are trying to update a post which does not belong to you");
        }

        currentPost.setTitle(postDto.getTitle());
        currentPost.setContent(postDto.getContent());
        currentPost.setLastUpdatedAt(LocalDateTime.now());
        postRepository.save(postMapper.postDtoToPostEntity(currentPost));
        return currentPost;
    }

    public ResponseEntity<String> deletePostById(Long id){
        UserDto currentUser = userService.getUserByUsername(SecurityUtils.getUserDetails().getUsername());
        PostDto currentPost = getPostById(id);

        if (!currentUser.getRole().equals(UserRole.ROLE_ADMIN)
                && !currentPost.getUser().getId().equals(currentUser.getId())) {
            throw new BadCredentialsException("You are trying to delete a post which does not belong to you");
        }

        postRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
