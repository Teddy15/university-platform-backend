package com.uni.platform.mapper;

import com.uni.platform.dto.category.CategoryDto;
import com.uni.platform.dto.comment.CommentDto;
import com.uni.platform.dto.post.CreatePostDto;
import com.uni.platform.dto.post.PostDto;
import com.uni.platform.dto.user.UserInfoDto;
import com.uni.platform.entity.Category;
import com.uni.platform.entity.Comment;
import com.uni.platform.entity.Post;
import com.uni.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface PostMapper {
    @Mapping(source = "user", target = "user", qualifiedByName = "userToUserPostDto")
    @Mapping(source = "category", target = "category", qualifiedByName = "categoryToCategoryDto")
    @Mapping(source = "comments", target = "comments", qualifiedByName = "commentToCommentDto")
    PostDto postEntityToPostDto(Post src);

    @Named("commentToCommentDto")
    default List<CommentDto> commentToCommentDto(List<Comment> comments){
        List<CommentDto> result = new ArrayList<>();

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

    @Mapping(source = "category", target = "category", qualifiedByName = "categoryDtoToCategory")
    Post postDtoToPostEntity(PostDto src);

    Post createPostDtoToPostEntity(CreatePostDto src);

    @Named("userToUserPostDto")
    default UserInfoDto userToUserPostDto(User user) {
        return new UserInfoDto(user.getId(), user.getUsername());
    }

    @Named("categoryToCategoryDto")
    default CategoryDto categoryToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(), category.getName(), category.getCreatedAt(), category.getLastUpdatedAt());
    }

    @Named("categoryDtoToCategory")
    default Category categoryDtoToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getCreatedAt(),
                categoryDto.getLastUpdatedAt());
    }
}
