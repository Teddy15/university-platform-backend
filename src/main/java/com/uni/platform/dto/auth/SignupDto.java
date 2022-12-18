package com.uni.platform.dto.auth;

import com.uni.platform.vo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDto {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    private UserRole role;
}
