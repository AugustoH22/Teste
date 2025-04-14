package com.adesp.festival.voting.infrastructure.mappers;

import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.voting.application.dtos.request.CreateDishVoteRequest;
import com.adesp.festival.voting.domain.entities.DishVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VotingMapper {

    @Mapping(target = "votingToken", source = "votingToken", qualifiedByName = "stringToVotingToken")
    DishVote createDishVoteRequestToDomain(CreateDishVoteRequest createDishVoteRequest);

    @Named("stringToVotingToken")
    default VotingToken stringToVotingToken(String votingToken){
        return new VotingToken(votingToken);
    }
}
