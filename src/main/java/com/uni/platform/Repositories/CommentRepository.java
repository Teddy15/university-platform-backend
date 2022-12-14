package com.uni.platform.Repositories;

import com.uni.platform.Enities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long id);

    @Query("SELECT c from comment c order by c.created_at")
    List<Comment> orderCommentByCreatedAt();
}
