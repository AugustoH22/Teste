package com.adesp.festival.tokens.application.services;

import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.entities.VotingTokenBatch;
import com.adesp.festival.tokens.domain.exceptions.NotFoundVotingTokenBatchException;
import com.adesp.festival.tokens.domain.repositories.VotingTokenBatchRepository;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;
import com.adesp.festival.voting.domain.exceptions.CPFHasAlreadyVotedForDishException;
import com.adesp.festival.voting.domain.exceptions.InvalidVotingTokenException;
import com.adesp.festival.voting.domain.exceptions.NotFoundVotingTokenException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotingTokenService {

    private final VotingTokenRepository votingTokenRepository;
    private final VotingTokenBatchService votingTokenBatchService;
    private final VotingTokenBatchRepository votingTokenBatchRepository;

    public VotingTokenService(VotingTokenRepository votingTokenRepository, VotingTokenBatchService votingTokenBatchService, VotingTokenBatchRepository votingTokenBatchRepository) {
        this.votingTokenRepository = votingTokenRepository;
        this.votingTokenBatchService = votingTokenBatchService;
        this.votingTokenBatchRepository = votingTokenBatchRepository;
    }

    public Boolean votingTokenExists(String votingToken){
        Optional<VotingToken> storedVotingToken = this.votingTokenRepository.findByVotingToken(votingToken);

        if(storedVotingToken.isPresent()){
            return true;
        }

        return false;
    }

    private String generateRandomVotingToken(){
        char[] specialChars = {'#', '@', '*'};
        String token = RandomStringUtils.random(8, true, true);

        while(this.votingTokenExists(token)){
            token = RandomStringUtils.random(8);
        }

        return token;
    }

    public List<VotingToken> generateVotingTokens(VotingTokenBatch votingTokenBatch){
        List<VotingToken> tokens = new ArrayList<>();

        for(int index = 0; index < votingTokenBatch.getQuantity(); index++){
            tokens.add(new VotingToken(
                    this.generateRandomVotingToken(),
                    votingTokenBatch
            ));
        }
        return this.votingTokenRepository.saveAll(tokens);
    }

    public Boolean isVotingTokenValid(VotingToken votingToken) {
        if (votingToken.getActive() == false) {
            throw new InvalidVotingTokenException();
        }

        if (votingToken.getDeleted()) {
            throw new InvalidVotingTokenException();
        }

        return true;
    }

    public VotingToken findVotingTokenByToken(String votingToken){
        return this.votingTokenRepository.findByVotingToken(votingToken)
                .orElseThrow(() -> new NotFoundVotingTokenException());
    }

    public void inactivateVotingToken(String token, String cpf){
        VotingToken votingToken = this.votingTokenRepository.findByVotingToken(token)
                .map(storedToken -> {
                    storedToken.setCpf(cpf);
                    storedToken.setActive(false);

                    return storedToken;
                })
                .orElseThrow(() -> new NotFoundVotingTokenException());
        this.votingTokenRepository.save(votingToken);
    }

    public Boolean cpfHasAlreadyVotedForDish(String votingToken, String cpf){
        Long dishId = this.getVotingTokenDishId(votingToken);
        Optional<VotingToken> token = this.votingTokenRepository.findVoteByCpfAndDish(cpf, dishId);

        if(token.isPresent()){
            throw new CPFHasAlreadyVotedForDishException();
        }

        return false;
    }

    public Long getVotingTokenDishId(String votingToken){
        VotingToken token = this.votingTokenRepository.findByVotingToken(votingToken)
                .orElseThrow(() -> new NotFoundVotingTokenException());
        return this.votingTokenBatchRepository.findById(token.getBatch().getId())
                .orElseThrow(() -> new NotFoundVotingTokenBatchException())
                .getId();
    }
}
