package com.uni.platform.service;

import com.uni.platform.dto.auth.SignupDto;
import com.uni.platform.dto.user.UpdateUserDto;
import com.uni.platform.entity.User;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.repository.UserRepository;
import com.uni.platform.util.SecurityUtils;
import com.uni.platform.vo.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private static final String CREATE_SUCCESS = "Successfully created a user account!";
    private static final String DELETE_SUCCESS = "Successfully deleted your user account!";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

//    public Set<Post> getUserPosts(Long userId) {
//        User user = userRepository.findById(userId).get();
//
//        return user.getPosts();
//    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.userEntityToUserDto(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Service called");
        return userMapper.userEntityToUserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No user found with id: " + id)));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.userEntityToUserDto(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new NoSuchElementException("No user found with username: " + username)));
    }

    @Override
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

    @Override
    public UpdateUserDto updateUserById(Long id, UpdateUserDto updateUserDto) {
        Optional<User> currentUser = userRepository.findByUsername(SecurityUtils.getUserDetails().getUsername());
        Optional<User> userToUpdate = userRepository.findById(id);

        if (currentUser.isEmpty() || userToUpdate.isEmpty()) {
            throw new UnsupportedOperationException("Error: User with id: " + id + " is not found!");
        }

        if (!currentUser.get().getRole().equals(UserRole.ROLE_ADMIN) && !id.equals(currentUser.get().getId())) {
            throw new BadCredentialsException("You are trying to update a profile for which you do not have " +
                    "the correct rights!");
        }

        User userEntity = userToUpdate.get();

        userEntity.setEmail(updateUserDto.getEmail());
        userEntity.setFullName(updateUserDto.getFullName());
        userEntity.setUsername(updateUserDto.getUsername());
        userEntity.setLastUpdatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
        return userMapper.userEntityToUpdateUserDto(userEntity);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Optional<User> currentUser = userRepository.findByUsername(SecurityUtils.getUserDetails().getUsername());
        Optional<User> userToUpdate = userRepository.findById(id);

        if (currentUser.isEmpty() || userToUpdate.isEmpty()) {
            throw new UnsupportedOperationException("Error: User with id: " + id + " is not found!");
        }

        if (!currentUser.get().getRole().equals(UserRole.ROLE_ADMIN) && !id.equals(currentUser.get().getId())) {
            throw new BadCredentialsException("You are trying to delete a profile for which you do not have " +
                    "the correct rights!");
        }

        userRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
