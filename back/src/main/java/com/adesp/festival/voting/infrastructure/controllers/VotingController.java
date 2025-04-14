package com.adesp.festival.voting.infrastructure.controllers;

import com.adesp.festival.voting.application.dtos.request.CreateDishVoteRequest;
import com.adesp.festival.voting.application.usecases.CreateDishVoteUseCase;
import com.adesp.festival.voting.domain.entities.DishVote;
import com.adesp.festival.voting.infrastructure.mappers.VotingMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VotingController {

    private final CreateDishVoteUseCase createDishVoteUseCase;
    private final VotingMapper votingMapper;

    public VotingController(CreateDishVoteUseCase createDishVoteUseCase, VotingMapper votingMapper) {
        this.createDishVoteUseCase = createDishVoteUseCase;
        this.votingMapper = votingMapper;
    }

    @PostMapping("/v1/voting/vote")
    public ResponseEntity<?> post(@RequestBody CreateDishVoteRequest createDishVoteRequest){
        DishVote dishVote = this.votingMapper.createDishVoteRequestToDomain(createDishVoteRequest);
        DishVote storedDishVote = this.createDishVoteUseCase.execute(dishVote, createDishVoteRequest.cpf());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
