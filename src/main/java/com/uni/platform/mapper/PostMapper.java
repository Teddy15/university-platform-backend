package com.uni.platform.mapper;

import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.user.UserInfoDto;
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
    @Mapping(source = "comments", target = "comments", qualifiedByName = "map")
    PostDto postEntityToPostDto(Post src);

    @Named("map")
    default Set<CommentDto> map(Set<Comment> comments){
        Set<CommentDto> result = new HashSet<>();

        for (Comment comment:comments) {
            UserInfoDto currentUserInfo = new UserInfoDto(comment.getUser().getId(), comment.getUser().getUsername());

            CommentDto currentCommentDto = new CommentDto();
            currentCommentDto.setId(comment.getId());
            currentCommentDto.setContent(comment.getContent());
            currentCommentDto.setUser(currentUserInfo);
            currentCommentDto.setCreated_at(comment.getCreated_at());
            currentCommentDto.setLast_updated_at(comment.getLast_updated_at());
            result.add(currentCommentDto);
        }

        return result;
    }

    List<PostDto> postEntityToPostDto(List<Post> src);

    Post postDtoToPostEntity(PostDto src);

    Post createPostDtoToPostEntity(CreatePostDto src);

    @Named("userToUserPostDto")
    default UserInfoDto userToUserPostDto(User user) {
        return new UserInfoDto(user.getId(), user.getUsername());
    }
}
