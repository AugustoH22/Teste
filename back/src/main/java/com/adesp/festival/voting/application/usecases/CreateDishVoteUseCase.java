package com.adesp.festival.voting.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import com.adesp.festival.tokens.application.services.VotingTokenService;
import com.adesp.festival.tokens.domain.entities.VotingToken;
import com.adesp.festival.tokens.domain.repositories.VotingTokenRepository;
import com.adesp.festival.voting.domain.entities.DishVote;
import com.adesp.festival.voting.domain.repositories.DishVoteRepository;

@UseCase
public class CreateDishVoteUseCase {

    private final DishVoteRepository dishVoteRepository;
    private final VotingTokenRepository votingTokenRepository;
    private final VotingTokenService votingTokenService;

    public CreateDishVoteUseCase(DishVoteRepository dishVoteRepository, VotingTokenRepository votingTokenRepository, VotingTokenService votingTokenService) {
        this.dishVoteRepository = dishVoteRepository;
        this.votingTokenRepository = votingTokenRepository;
        this.votingTokenService = votingTokenService;
    }

    public DishVote execute(DishVote dishVote, String cpf){
        VotingToken votingToken = this.votingTokenService.findVotingTokenByToken(dishVote.getVotingToken().getVotingToken());
        dishVote.setVotingToken(new VotingToken(votingToken.getId()));
        this.votingTokenService.isVotingTokenValid(votingToken);
        this.votingTokenService.cpfHasAlreadyVotedForDish(votingToken.getVotingToken(), cpf);
        this.votingTokenService.inactivateVotingToken(votingToken.getVotingToken(), cpf);
        return this.dishVoteRepository.save(dishVote);
    }
}
