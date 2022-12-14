package com.uni.platform.service;

import com.uni.platform.dto.auth.SignupDto;
import com.uni.platform.entity.User;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.repository.UserRepository;
import com.uni.platform.vo.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class UserService {
    private static final String CREATE_SUCCESS = "Successfully created a user account!";
    private static final String UPDATE_SUCCESS = "Successfully updated your user account!";
    private static final String DELETE_SUCCESS = "Successfully deleted your user account!";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.userEntityToUserDto(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        log.info("Service called");
        return userMapper.userEntityToUserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No user found with id: " + id)));
    }

    public UserDto getUserByUsername(String username) {
        return userMapper.userEntityToUserDto(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new NoSuchElementException("No user found with username: " + username)));
    }

    public ResponseEntity<String> insertUser(SignupDto signupDto) {
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signupDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = userMapper.signupDtoToUserEntity(signupDto);
        user.setPassword(encoder.encode(signupDto.getPassword()));
        user.setRegisteredAt(LocalDateTime.now());
        user.setLastUpdatedAt(LocalDateTime.now());

        if (UserRole.ROLE_ADMIN.getDescription().equalsIgnoreCase(signupDto.getRole().getDescription())) {
            user.setRole(UserRole.getValueByDescription(UserRole.ROLE_ADMIN.getDescription())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        } else {
            user.setRole(UserRole.getValueByDescription(UserRole.ROLE_USER.getDescription())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        }

        userRepository.save(user);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    public User updateUserById(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(userDto.getEmail());
                    user.setFullName(userDto.getFullName());
                    user.setUsername(userDto.getUsername());
                    user.setLastUpdatedAt(LocalDateTime.now());
                    return user;
                })
                .orElseThrow(() -> new RuntimeException("Error: User is not found."));
    }

    public ResponseEntity<String> deleteById(Long id) {
        getUserById(id);

        userRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
