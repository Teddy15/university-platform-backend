package com.uni.platform.Repositories;

import com.uni.platform.Enities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Integer id);

    Optional<Comment> findCommentByContentAndTitle(String content, String title);
}
