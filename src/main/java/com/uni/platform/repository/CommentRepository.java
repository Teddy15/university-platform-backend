package com.uni.platform.repository;

import com.uni.platform.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long id);

    @Query("SELECT c from comment c order by c.createdAt")
    List<Comment> orderCommentByCreatedAt();
}
