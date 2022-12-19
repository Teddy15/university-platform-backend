package com.uni.platform.repository;

import com.uni.platform.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    @Query("SELECT COUNT(r) " +
            "FROM reaction r " +
            "WHERE r.postId=postId ")
    Long getReactionCountByPost(@Param("postId") Long postId);
}


