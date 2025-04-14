package com.adesp.festival.tokens.infrastructure.controllers;

import com.adesp.festival.tokens.application.dtos.request.CreateVotingTokenBatchRequest;
import com.adesp.festival.tokens.application.dtos.response.CreateVotingTokenBatchResponse;
import com.adesp.festival.tokens.application.usecases.CreateVotingTokenBatchUseCase;
import com.adesp.festival.tokens.application.usecases.PrintVotingTokenBatchUseCase;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import com.adesp.festival.tokens.infrastructure.mappers.VotingTokenBatchMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api")
public class VotingTokenController {

    private final CreateVotingTokenBatchUseCase createVotingTokenBatchUseCase;
    private final PrintVotingTokenBatchUseCase printVotingTokenBatchUseCase;
    private final VotingTokenBatchMapper votingTokenBatchMapper;

    public VotingTokenController(CreateVotingTokenBatchUseCase createVotingTokenBatchUseCase, PrintVotingTokenBatchUseCase printVotingTokenBatchUseCase, VotingTokenBatchMapper votingTokenBatchMapper) {
        this.createVotingTokenBatchUseCase = createVotingTokenBatchUseCase;
        this.printVotingTokenBatchUseCase = printVotingTokenBatchUseCase;
        this.votingTokenBatchMapper = votingTokenBatchMapper;
    }

    @PostMapping("/v1/vote/batch")
    public ResponseEntity<CreateVotingTokenBatchResponse> postVotingTokenBatch(@RequestBody CreateVotingTokenBatchRequest createVotingTokenBatchRequest){
        VotingTokenBatch votingTokenBatch = this.votingTokenBatchMapper.createVotingTokenBatchRequestToDomain(createVotingTokenBatchRequest);
        VotingTokenBatch storedTokenBatch = this.createVotingTokenBatchUseCase.execute(votingTokenBatch);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.votingTokenBatchMapper.domainToCreateVotingTokenBatchResponse(storedTokenBatch));
    }

    @GetMapping("/v1/vote/batch/print/{id}")
    public ResponseEntity<?> printVotingTokenBatch(@PathVariable Long id){
        ByteArrayOutputStream output= this.printVotingTokenBatchUseCase.execute(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(output.size());
        return new ResponseEntity<>(output.toByteArray(), headers, HttpStatus.OK);
    }
}
