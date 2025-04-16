package com.adesp.festival.tokens.infrastructure.controllers;

import com.adesp.festival.tokens.application.dtos.request.CreateVotingTokenBatchRequest;
import com.adesp.festival.tokens.application.dtos.response.CreateVotingTokenBatchResponse;
import com.adesp.festival.tokens.application.usecases.CreateVotingTokenBatchUseCase;
import com.adesp.festival.tokens.application.usecases.GetVotingInfosUseCase;
import com.adesp.festival.tokens.application.usecases.PrintVotingTokenBatchUseCase;
import com.adesp.festival.tokens.application.usecases.VerifyVotingTokenStatusUseCase;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import com.adesp.festival.tokens.infrastructure.mappers.VotingTokenBatchMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VotingTokenController {

    private final CreateVotingTokenBatchUseCase createVotingTokenBatchUseCase;
    private final PrintVotingTokenBatchUseCase printVotingTokenBatchUseCase;
    private final VerifyVotingTokenStatusUseCase verifyVotingTokenStatusUseCase;
    private final GetVotingInfosUseCase getVotingInfosUseCase;
    private final VotingTokenBatchMapper votingTokenBatchMapper;

    public VotingTokenController(CreateVotingTokenBatchUseCase createVotingTokenBatchUseCase, PrintVotingTokenBatchUseCase printVotingTokenBatchUseCase, VerifyVotingTokenStatusUseCase verifyVotingTokenStatusUseCase, GetVotingInfosUseCase getVotingInfosUseCase, VotingTokenBatchMapper votingTokenBatchMapper) {
        this.createVotingTokenBatchUseCase = createVotingTokenBatchUseCase;
        this.printVotingTokenBatchUseCase = printVotingTokenBatchUseCase;
        this.verifyVotingTokenStatusUseCase = verifyVotingTokenStatusUseCase;
        this.getVotingInfosUseCase = getVotingInfosUseCase;
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

    @GetMapping("/v1/vote/voting-token/status")
    public ResponseEntity<?> verifyToken(@RequestParam("votingToken") String votingToken){
        VotingToken token = this.verifyVotingTokenStatusUseCase.execute(votingToken);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.getVotingInfosUseCase.execute(votingToken));
    }
}
