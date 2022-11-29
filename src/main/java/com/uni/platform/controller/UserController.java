package com.uni.platform.controller;

import com.uni.platform.dto.user.CreateUserDto;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers() called");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserByUsername(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<String> insertUser(@Validated @RequestBody CreateUserDto createUserDto) {
        log.info("insertUser() called");
        return userService.insertUser(createUserDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserByUsername(@PathVariable Long userId,
                                                       @Validated @RequestBody UserDto userDto) {
        return userService.updateUserById(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
