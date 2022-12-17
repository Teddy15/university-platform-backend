package com.uni.platform.controller;

import com.uni.platform.dto.reaction.CreateReactionDto;
import com.uni.platform.dto.reaction.ReactionDto;
import com.uni.platform.service.ReactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@Validated
@RequestMapping("/uni-platform/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping
    public ResponseEntity<String> createReaction(@Validated @RequestBody CreateReactionDto createReactionDto) {
        log.info("createReaction() called");
        return reactionService.createReactionDto(createReactionDto);
    }

    @PutMapping("/{reactionId}")
    public ResponseEntity<String> updateReaction(@PathVariable Long reactionId, @RequestBody ReactionDto reactionDto) {
        log.info("updateReaction() called");
        return reactionService.updateReaction(reactionId, reactionDto);
    }

    @GetMapping("/{reactionId}")
    public ReactionDto getReactionById(@PathVariable Long reactionId) {
        log.info("getReactionById() called");
        return reactionService.getReactionById(reactionId);
    }
}
