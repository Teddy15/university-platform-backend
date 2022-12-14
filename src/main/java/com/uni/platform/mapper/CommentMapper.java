package com.uni.platform.mapper;

import com.uni.platform.entity.Comment;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.comment.CreateCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface CommentMapper {

    CommentDto commentEntityToCommentDto(Comment src);

    List<CommentDto> commentEntityToCommentDto(List<Comment> src);

    Comment commentDtoToCommentEntity(CommentDto src);

    Comment createCommentDtoToCommentEntity(CreateCommentDto src);
}
