package com.adesp.festival.tokens.application.services;

import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.exceptions.NotFoundVotingTokenBatchException;
import com.adesp.festival.tokens.domain.repositories.VotingTokenBatchRepository;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;
import com.adesp.festival.voting.domain.exceptions.NotFoundVotingTokenException;
import org.springframework.stereotype.Service;

@Service
public class VotingTokenBatchService {

    private final VotingTokenBatchRepository votingTokenBatchRepository;
    private final VotingTokenRepository votingTokenRepository;

    public VotingTokenBatchService(VotingTokenBatchRepository votingTokenBatchRepository, VotingTokenRepository votingTokenRepository) {
        this.votingTokenBatchRepository = votingTokenBatchRepository;
        this.votingTokenRepository = votingTokenRepository;
    }

    public Long getVotingTokenDishId(String votingToken){
        VotingToken token = this.votingTokenRepository.findByVotingToken(votingToken)
                .orElseThrow(() -> new NotFoundVotingTokenException());
        return this.votingTokenBatchRepository.findById(token.getBatch().getId())
                .orElseThrow(() -> new NotFoundVotingTokenBatchException())
                .getId();
    }
}
