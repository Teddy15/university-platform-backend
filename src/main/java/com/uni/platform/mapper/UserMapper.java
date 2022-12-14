package com.uni.platform.mapper;

import com.uni.platform.dto.auth.SignupDto;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface UserMapper {
    UserDto userEntityToUserDto(User src);

    List<UserDto> userEntityToUserDto(List<User> src);

    User userDtoToUserEntity(UserDto src);

    User signupDtoToUserEntity(SignupDto src);
}
