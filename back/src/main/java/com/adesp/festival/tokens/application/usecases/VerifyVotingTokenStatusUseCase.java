package com.adesp.festival.tokens.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;
import com.adesp.festival.voting.domain.exceptions.NotFoundVotingTokenException;

import java.util.Optional;

@UseCase
public class VerifyVotingTokenStatusUseCase {

    private final VotingTokenRepository votingTokenRepository;

    public VerifyVotingTokenStatusUseCase(VotingTokenRepository votingTokenRepository) {
        this.votingTokenRepository = votingTokenRepository;
    }

    public VotingToken execute(String votingToken){
        return this.votingTokenRepository.findByVotingToken(votingToken)
                .orElseThrow(() -> new NotFoundVotingTokenException());
    }
}
