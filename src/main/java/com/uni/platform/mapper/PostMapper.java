package com.uni.platform.mapper;

import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.user.UserPostDto;
import com.uni.platform.entity.Comment;
import com.uni.platform.entity.Post;
import com.uni.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface PostMapper {
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserPostDto")
    PostDto postEntityToPostDto(Post src);

    List<PostDto> postEntityToPostDto(List<Post> src);

    Post postDtoToPostEntity(PostDto src);

    Post createPostDtoToPostEntity(CreatePostDto src);

    @Named("userToUserPostDto")
    default UserPostDto userToUserPostDto(User user) {
        return new UserPostDto(user.getId(), user.getUsername());
    }

//    @Named("commentToCommentPostDto")
//    default Set<CommentDto> CommentDto(Set<Comment> comments) {
//        Set<CommentDto> result = new HashSet<>();
//
//        for (Comment comment: comments) {
//            CommentDto currentComment = new CommentDto();
//            currentComment.setId(comment.getId());
//            currentComment.setContent(comment.getContent());
//            currentComment.setCreated_at(comment.getCreated_at());
//            currentComment.setLast_updated_at(comment.getLast_updated_at());
//
//            result.add(currentComment);
//        }
//
//        return result;
//    }
}
