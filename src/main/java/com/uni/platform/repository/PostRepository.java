package com.uni.platform.repository;

import com.uni.platform.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p " +
            "FROM post p order by p.id")
    Page<Post> filterPostPages(Pageable pageable);

    Optional<Post> findByTitle(String title);
}
