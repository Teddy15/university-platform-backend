package com.uni.platform.service.comment;

import com.uni.platform.dto.user.UserDto;
import com.uni.platform.entity.Comment;
import com.uni.platform.mapper.PostMapper;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.repository.CommentRepository;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.comment.CreateCommentDto;
import com.uni.platform.mapper.CommentMapper;
import com.uni.platform.service.post.PostServiceImpl;
import com.uni.platform.service.user.UserServiceImpl;
import com.uni.platform.util.SecurityUtils;
import com.uni.platform.vo.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentsServiceImpl implements CommentsService {

    private static final String CREATE_SUCCESS = "Successfully created a comment!";
    private static final String UPDATE_SUCCESS = "Successfully updated your comment!";
    private static final String DELETE_SUCCESS = "Successfully deleted your comment";

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final PostServiceImpl postServiceImpl;

    private final PostMapper postMapper;

    private final UserServiceImpl userServiceImpl;

    private final UserMapper userMapper;

    public CommentsServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper,
                               PostServiceImpl postServiceImpl, PostMapper postMapper, UserServiceImpl userServiceImpl, UserMapper userMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postServiceImpl = postServiceImpl;
        this.postMapper = postMapper;
        this.userServiceImpl = userServiceImpl;
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
        comment.setCreatedAt(created_at);
        comment.setLastUpdatedAt(created_at);

        comment.setPost(
                postMapper.postDtoToPostEntity(
                        postServiceImpl.getPostById(
                                createCommentDto.getPostId())));

        comment.setUser(
                userMapper.userDtoToUserEntity(
                        userServiceImpl.getUserByUsername(
                                SecurityUtils.getUserDetails().getUsername())));

        commentRepository.save(comment);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteComment(Long id) {
        UserDto currentUser = userServiceImpl.getUserByUsername(
                SecurityUtils.getUserDetails().getUsername());
        CommentDto currentComment = getCommentsById(id);

        if (!currentUser.getRole().equals(UserRole.ROLE_ADMIN)
                && !currentComment.getUser().getId().equals(currentUser.getId())) {
            throw new BadCredentialsException("You are trying to delete a comment which does not belong to you");
        }

        commentRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateComment(CommentDto commentDto, Long id) {
        UserDto currentUser = userServiceImpl.getUserByUsername(
                SecurityUtils.getUserDetails().getUsername());
        CommentDto currentComment = getCommentsById(id);

        if (!currentUser.getRole().equals(UserRole.ROLE_ADMIN)
                && !currentComment.getUser().getId().equals(currentUser.getId())) {
            throw new BadCredentialsException("You are trying to delete a comment which does not belong to you");
        }

        currentComment.setContent(commentDto.getContent());
        currentComment.setLastUpdatedAt(LocalDateTime.now());

        commentRepository.save(commentMapper.commentDtoToCommentEntity(currentComment));
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }
}
