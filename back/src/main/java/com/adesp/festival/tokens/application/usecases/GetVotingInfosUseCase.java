package com.adesp.festival.tokens.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.tokens.application.dtos.response.VotingInfosResponse;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;
import com.adesp.festival.voting.domain.exceptions.NotFoundVotingTokenException;

@UseCase
public class GetVotingInfosUseCase {

    private final VotingTokenRepository votingTokenRepository;

    public GetVotingInfosUseCase(VotingTokenRepository votingTokenRepository) {
        this.votingTokenRepository = votingTokenRepository;
    }

    public VotingInfosResponse execute(String votingToken){
        return this.votingTokenRepository.findVotingInfos(votingToken)
                .orElseThrow(() -> new NotFoundVotingTokenException());
    }
}
