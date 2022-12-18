package com.uni.platform.mapper;

import com.uni.platform.dto.attachment.AttachmentInfoDto;
import com.uni.platform.dto.user.UserInfoDto;
import com.uni.platform.entity.Attachment;
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

    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserInfoDto")
    @Mapping(source = "attachment", target = "attachment", qualifiedByName = "attachmentToAttachmentInfoDto")
    CommentDto commentEntityToCommentDto(Comment src);

    List<CommentDto> commentEntityToCommentDto(List<Comment> src);

    Comment commentDtoToCommentEntity(CommentDto src);

    Comment createCommentDtoToCommentEntity(CreateCommentDto src);

    @Named("userToUserInfoDto")
    default UserInfoDto userToUserInfoDto(User user) {

        return new UserInfoDto(user.getId(), user.getUsername());
    }

    @Named("attachmentToAttachmentInfoDto")
    default AttachmentInfoDto attachmentToAttachmentInfoDto(Attachment attachment) {
        AttachmentInfoDto result = new AttachmentInfoDto();
        result.setId(attachment.getId());
        result.setAttachmentName(attachment.getAttachmentName());
        result.setAttachmentType(attachment.getAttachmentType());

        return result;
    }
}
