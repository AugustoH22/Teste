package com.adesp.festival.tokens.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.tokens.application.services.ReportService;
import com.adesp.festival.tokens.application.services.VotingTokenService;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import com.adesp.festival.tokens.domain.repositories.VotingTokenBatchRepository;

import java.util.List;

@UseCase
public class CreateVotingTokenBatchUseCase {

    private final VotingTokenService votingTokenService;
    private final VotingTokenBatchRepository votingTokenBatchRepository;

    public CreateVotingTokenBatchUseCase(VotingTokenService votingTokenService, VotingTokenBatchRepository votingTokenBatchRepository) {
        this.votingTokenService = votingTokenService;
        this.votingTokenBatchRepository = votingTokenBatchRepository;
    }

    public VotingTokenBatch execute(VotingTokenBatch votingTokenBatch){
        VotingTokenBatch storedVotingTokenBatch = this.votingTokenBatchRepository.save(votingTokenBatch);
        List<VotingToken> tokens = this.votingTokenService.generateVotingTokens(storedVotingTokenBatch);
        return storedVotingTokenBatch;
    }
}
