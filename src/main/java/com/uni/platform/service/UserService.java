package com.uni.platform.service;

import com.uni.platform.dto.auth.SignupDto;
import com.uni.platform.dto.user.UpdateUserDto;
import com.uni.platform.dto.user.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();

    public UserDto getUserById(Long id);

    public UserDto getUserByUsername(String username);

    public ResponseEntity<String> insertUser(SignupDto signupDto);

    public UpdateUserDto updateUserById(Long id, UpdateUserDto updateUserDto);

    public ResponseEntity<String> deleteById(Long id);
}
