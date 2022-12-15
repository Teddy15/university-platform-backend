package com.uni.platform.repository;

import com.uni.platform.entity.Reaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    @Query("SELECT r " +
            "FROM reaction r ")
    Page<Reaction> filterReactionPages(Pageable pageable);

    Optional<Reaction> findByPostId(Long postId);
}


