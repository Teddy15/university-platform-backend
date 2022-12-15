package com.uni.platform.mapper;

import com.uni.platform.dto.user.UserCommentDto;
import com.uni.platform.entity.Comment;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.comment.CreateCommentDto;
import com.uni.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface CommentMapper {

    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserCommentDto")
    CommentDto commentEntityToCommentDto(Comment src);

    List<CommentDto> commentEntityToCommentDto(List<Comment> src);

    Comment commentDtoToCommentEntity(CommentDto src);

    Comment createCommentDtoToCommentEntity(CreateCommentDto src);

    @Named("userToUserCommentDto")
    default UserCommentDto userToUserCommentDto(User user) {
        return new UserCommentDto(user.getId(), user.getUsername());
    }
}
