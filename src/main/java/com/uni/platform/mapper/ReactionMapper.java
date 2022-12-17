package com.uni.platform.mapper;

import com.uni.platform.dto.reaction.CreateReactionDto;
import com.uni.platform.dto.reaction.ReactionDto;
import com.uni.platform.entity.Reaction;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface ReactionMapper {
    ReactionDto reactionEntityToReactionDto(Reaction src);

    Reaction reactionDtoToReactionEntity(ReactionDto src);

    Reaction createReactionDtoToReactionEntity(CreateReactionDto src);
}
