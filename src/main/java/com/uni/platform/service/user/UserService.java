package com.uni.platform.service.user;

import com.uni.platform.dto.auth.SignupDto;
import com.uni.platform.dto.user.UpdateUserDto;
import com.uni.platform.dto.user.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    ResponseEntity<String> insertUser(SignupDto signupDto);

    UpdateUserDto updateUserById(Long id, UpdateUserDto updateUserDto);

    ResponseEntity<String> deleteById(Long id);
}
