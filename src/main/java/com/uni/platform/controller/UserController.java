package com.uni.platform.controller;

import com.uni.platform.dto.user.UpdateUserDto;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.entity.Post;
import com.uni.platform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers() called");
        return userService.getAllUsers();
    }

//    @GetMapping("/{userId}/posts")
//    public Set<Post> getUserPosts(@PathVariable Long userId) {
//        log.info("getAllUsers() called");
//        return userService.getUserPosts(userId);
//    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserDto getUserById(@PathVariable Long userId) {
        log.info("getUser() called");
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UpdateUserDto updateUserById(@PathVariable Long userId,
                                              @Validated @RequestBody UpdateUserDto userDto) {
        return userService.updateUserById(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }
}
