package com.uni.platform.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private Long id;
    private String username;
    private String email;
    private String fullName;
    List<String> roles;
}
