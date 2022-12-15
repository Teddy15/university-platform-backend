package com.uni.platform.service;

import com.uni.platform.dto.reaction.CreateReactionDto;
import com.uni.platform.dto.reaction.ReactionDto;
import com.uni.platform.entity.Reaction;
import com.uni.platform.mapper.ReactionMapper;
import com.uni.platform.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReactionService {

    private static final String REACTION_MESSAGE = "Reaction reacted!";
    private static final String CHANGE_REACTION_MESSAGE = "Reaction changed!";

    private final ReactionMapper mapper;
    private final ReactionRepository repo;

    @Autowired
    public ReactionService(ReactionMapper mapper, ReactionRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public Integer getLikesForUser(int currentPage, int itemsPerPage, String user) {
        Pageable pageable = PageRequest.of(currentPage - 1, itemsPerPage);
        Page<Reaction> reactionPage = repo.filterReactionPages(pageable);

        Integer count = 0;
        for (Reaction x : reactionPage) {
            if (user.toLowerCase().equals(x.getReactedBy()))
                if (x.getIsPositive()) {
                    count++;
                }
        }

        return count;
    }

    public Integer getDisLikes(int currentPage, int itemsPerPage, String user) {
        Pageable pageable = PageRequest.of(currentPage - 1, itemsPerPage);
        Page<Reaction> reactionPage = repo.filterReactionPages(pageable);

        Integer count = 0;
        for (Reaction x : reactionPage) {
            if (user.toLowerCase().equals(x.getReactedBy()))
                if (!x.getIsPositive()) {
                    count++;
                }
        }

        return count;
    }

    public ResponseEntity<String> createReactionDto(CreateReactionDto createReactionDto) {
        Reaction reaction = mapper.createReactionDtoToReactionEntity(createReactionDto);

        repo.save(reaction);
        return new ResponseEntity<>(REACTION_MESSAGE, HttpStatus.OK);
    }

    public ReactionDto getReactionById(Long id){
        return mapper
                .reactionEntityToReactionDto(
                        repo.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("No reaction is created with " + id)));
    }

    public ResponseEntity<String> updateReaction(Long id, ReactionDto reactionDto){
        getReactionById(id);

        Reaction reaction = mapper.reactionDtoToReactionEntity(reactionDto);
        repo.save(reaction);

        return new ResponseEntity<>(CHANGE_REACTION_MESSAGE, HttpStatus.OK);
    }
}
