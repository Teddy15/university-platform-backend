package com.uni.platform.entity;

import com.uni.platform.vo.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name="user")
@Table(name="user", schema="uni_platform",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
})
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 30)
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(max = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime lastUpdatedAt;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    public User() {

    }

    public User(String username, String email, String fullName, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }
}
