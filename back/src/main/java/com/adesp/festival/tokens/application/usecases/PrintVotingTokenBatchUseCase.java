package com.adesp.festival.tokens.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.tokens.application.services.ReportService;
import com.adesp.festival.tokens.application.services.VotingTokenService;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;

import java.io.ByteArrayOutputStream;
import java.util.List;

@UseCase
public class PrintVotingTokenBatchUseCase {

    private final VotingTokenService votingTokenService;
    private final ReportService reportService;
    private final VotingTokenRepository votingTokenRepository;

    public PrintVotingTokenBatchUseCase(VotingTokenService votingTokenService, ReportService reportService, VotingTokenRepository votingTokenRepository) {
        this.votingTokenService = votingTokenService;
        this.reportService = reportService;
        this.votingTokenRepository = votingTokenRepository;
    }

    public ByteArrayOutputStream execute(Long votingTokenBatchId){
        List<VotingToken> tokens = this.votingTokenRepository.findByBatchId(votingTokenBatchId);
        return this.reportService.printVotingTokensBatch(tokens);
    }
}
