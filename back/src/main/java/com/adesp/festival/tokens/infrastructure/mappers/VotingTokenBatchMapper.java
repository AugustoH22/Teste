package com.adesp.festival.tokens.infrastructure.mappers;

import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.tokens.application.dtos.request.CreateVotingTokenBatchRequest;
import com.adesp.festival.tokens.application.dtos.response.CreateVotingTokenBatchResponse;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VotingTokenBatchMapper {

    @Mapping(target = "dish", source = "dishId", qualifiedByName = "longToDish")
    VotingTokenBatch createVotingTokenBatchRequestToDomain(CreateVotingTokenBatchRequest createVotingTokenBatchRequest);

    @Mapping(target = "dishId", source = "dish", qualifiedByName = "dishToLong")
    CreateVotingTokenBatchResponse domainToCreateVotingTokenBatchResponse(VotingTokenBatch votingTokenBatch);

    @Named("longToDish")
    default Dish longToDish(Long dishId){
        return new Dish(dishId);
    }

    @Named("dishToLong")
    default Long dishToLong(Dish dish){
        return dish.getId();
    }
}
