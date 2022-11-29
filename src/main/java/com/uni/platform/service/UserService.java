package com.uni.platform.service;

import com.uni.platform.dto.user.CreateUserDto;
import com.uni.platform.mapper.UserMapper;
import com.uni.platform.dto.user.UserDto;
import com.uni.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private static final String CREATE_SUCCESS = "Successfully created a user account!";
    private static final String UPDATE_SUCCESS = "Successfully updated your user account!";
    private static final String DELETE_SUCCESS = "Successfully deleted your user account!";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userMapper.userEntityToUserDto(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        return userMapper.userEntityToUserDto(
                userRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("No user found with id: " + id)));
    }

    public UserDto getUserByUsername(String username) {
        return userMapper.userEntityToUserDto(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new NoSuchElementException("No user found with username: " + username)));
    }

    public ResponseEntity<String> insertUser(CreateUserDto createUserDto) {
        userRepository.save(userMapper.createUserDtoToUserEntity(createUserDto));
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> updateUserById(Long id, UserDto userDto) {
        getUserById(id);
        userRepository.save(userMapper.userDtoToUserEntity(userDto));
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteById(Long id) {
        getUserById(id);

        userRepository.deleteById(id);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}
