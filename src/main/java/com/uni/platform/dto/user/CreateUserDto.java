package com.uni.platform.dto.user;

import com.uni.platform.vo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String password;

    private UserRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime lastUpdatedAt;
}
