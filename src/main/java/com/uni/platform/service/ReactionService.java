package com.uni.platform.service;

import com.uni.platform.dto.reaction.CreateReactionDto;
import com.uni.platform.dto.reaction.ReactionDto;
import com.uni.platform.entity.Reaction;
import com.uni.platform.mapper.ReactionMapper;
import com.uni.platform.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReactionService {

    private static final String REACTION_MESSAGE = "Reacted!";
    private static final String CHANGE_REACTION_MESSAGE = "Reaction changed!";
    private static final String REACTION_DELETED_MESSAGE = "Reaction removed!";

    private final ReactionMapper mapper;
    private final ReactionRepository repo;

    @Autowired
    public ReactionService(ReactionMapper mapper, ReactionRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public ReactionDto insertReactionDto(CreateReactionDto createReactionDto) {
        Reaction reaction = mapper.createReactionDtoToReactionEntity(createReactionDto);

        repo.save(reaction);

        ReactionDto reactionDto = mapper.reactionEntityToReactionDto(reaction);
        return reactionDto;
    }

    public ReactionDto getReactionById(Long id) {
        return mapper
                .reactionEntityToReactionDto(
                        repo.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("No reaction is created with id = " + id)));
    }

    public ResponseEntity<String> updateReaction(Long id, ReactionDto reactionDto) {
        Reaction reaction = mapper.reactionDtoToReactionEntity(reactionDto);
        repo.save(reaction);

        return new ResponseEntity<>(CHANGE_REACTION_MESSAGE, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteReactionById(Long id) {
        repo.deleteById(id);

        return new ResponseEntity<>(REACTION_DELETED_MESSAGE, HttpStatus.OK);
    }
}
