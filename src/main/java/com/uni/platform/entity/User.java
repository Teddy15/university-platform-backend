package com.uni.platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uni.platform.vo.UserRole;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name="user")
@Table(name="user", schema="uni_platform",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @JsonIgnore
    @ToString.Exclude
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<Comment> comments;

    public User() {

    }

    public User(String username, String email, String fullName, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
