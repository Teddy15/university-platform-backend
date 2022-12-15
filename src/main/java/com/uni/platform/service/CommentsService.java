package com.uni.platform.service;

import com.uni.platform.entity.Comment;
import com.uni.platform.entity.User;
import com.uni.platform.mapper.PostMapper;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.repository.CommentRepository;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.comment.CreateCommentDto;
import com.uni.platform.mapper.CommentMapper;
import com.uni.platform.util.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentsService implements ICommentsService {

    private static final String CREATE_SUCCESS = "Successfully created a comment!";
    private static final String UPDATE_SUCCESS = "Successfully updated your comment!";
    private static final String DELETE_SUCCESS = "Successfully deleted your comment";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final PostService postService;

    private final PostMapper postMapper;

    private final UserService userService;

    private final UserMapper userMapper;

    public CommentsService(CommentRepository commentRepository, CommentMapper commentMapper,
                           PostService postService, PostMapper postMapper, UserService userService, UserMapper userMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
        this.postMapper = postMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public CommentDto getCommentsById(Long id) {
        return commentMapper.commentEntityToCommentDto(
                commentRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No comment found with id: " + id)));
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentMapper.commentEntityToCommentDto(commentRepository.orderCommentByCreatedAt());
    }

    @Override
    public ResponseEntity<String> createComment(CreateCommentDto createCommentDto) {

        Comment comment = commentMapper.createCommentDtoToCommentEntity(createCommentDto);

        LocalDateTime created_at = LocalDateTime.now();
        comment.setCreated_at(created_at);
        comment.setLast_updated_at(created_at);

        comment.setPost(
                postMapper.postDtoToPostEntity(
                        postService.getPostById(
                                createCommentDto.getPostId())));

        comment.setUser(
                userMapper.userDtoToUserEntity(
                        userService.getUserByUsername(
                                SecurityUtils.getUserDetails().getUsername())));

        commentRepository.save(comment);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteComment(Long id) {
        getCommentsById(id);
        commentRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateComment(CommentDto commentDto, Long id) {

        Comment comment = commentMapper.commentDtoToCommentEntity(getCommentsById(id));

        LocalDateTime last_updated_at = LocalDateTime.now();
        comment.setLast_updated_at(last_updated_at);
        comment.setContent(commentDto.getContent());

        commentRepository.save(comment);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }
}